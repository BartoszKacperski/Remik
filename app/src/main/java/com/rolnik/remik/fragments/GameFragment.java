package com.rolnik.remik.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.MyApplication;
import com.rolnik.remik.R;
import com.rolnik.remik.adapters.PlayerGameRecyclerViewAdapter;
import com.rolnik.remik.daos.GameDao;
import com.rolnik.remik.daos.GameHistoryDao;
import com.rolnik.remik.daos.PlayerDao;
import com.rolnik.remik.dialogs.AddPlayerPointsDialog;
import com.rolnik.remik.dialogs.ChoicePlayersDialog;
import com.rolnik.remik.dialogs.EndGameDialog;
import com.rolnik.remik.model.CurrentGame;
import com.rolnik.remik.model.Game;
import com.rolnik.remik.model.GameHistory;
import com.rolnik.remik.model.Player;
import com.rolnik.remik.model.PlayerWithPoints;
import com.rolnik.remik.utils.CurrentGameSaveState;
import com.rolnik.remik.utils.OnItemClicked;
import com.rolnik.remik.utils.PlayerPointsSpeechService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;

import static android.app.Activity.RESULT_OK;

public class GameFragment extends Fragment {
    private static final int SPEECH_REQUEST_CODE = 10;

    @BindView(R.id.gamePlayers)
    RecyclerView gamePlayers;
    @BindView(R.id.endRound)
    Button endRound;
    @BindView(R.id.finishButton)
    Button finish;
    @BindView(R.id.pointsBySpeech)
    ImageButton pointsBySpeech;

    private CompositeDisposable compositeDisposable;
    private Context mContext;
    private ChoicePlayersDialog choicePlayersDialog;
    private PlayerGameRecyclerViewAdapter adapter;

    private ObservableField<CurrentGame> currentGameField;

    @Inject
    PlayerDao playerDao;
    @Inject
    GameHistoryDao gameHistoryDao;
    @Inject
    GameDao gameDao;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        MyApplication.app().databaseComponent().inject(this);

        compositeDisposable = new CompositeDisposable();
        currentGameField = new ObservableField<>();

        initChoiceDialog();
        initGamePlayers();
        loadPlayers();
        initCurrentGame();
        tryToRestoreCurrentGame();
    }

    @Override
    public void onPause() {
        super.onPause();
        saveCurrentGame();
    }

    private void tryToRestoreCurrentGame() {
        CurrentGameSaveState currentGameSaveState = new CurrentGameSaveState(mContext);

        CurrentGame currentGame = currentGameSaveState.restoreOrNewGame();

        currentGameField.set(currentGame);
    }

    private void saveCurrentGame() {
        CurrentGameSaveState currentGameSaveState = new CurrentGameSaveState(mContext);

        currentGameSaveState.save(currentGameField.get());
    }

    @OnClick(R.id.startButton)
    public void showChooseDialog() {
        choicePlayersDialog.show();
    }


    @OnClick(R.id.finishButton)
    public void finishGame() {
        showResult();
        saveGameResultAndFinish();
    }

    private void showResult() {
        if(currentGameField.get() != null) {
            EndGameDialog endGameDialog = new EndGameDialog(mContext, currentGameField.get().getPlayers());

            endGameDialog.show();
        }
    }

    @OnClick(R.id.endRound)
    public void endRound() {
        if (currentGameField.get() != null) {
            showAddPlayerPointsDialog(Objects.requireNonNull(currentGameField.get()).getPlayers());
        }
    }

    @OnClick(R.id.pointsBySpeech)
    public void addPointsWithSpeech() {
        startSpeechRecognizer();
    }

    private Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            startNewCurrentGame();
        }
    };

    private void initCurrentGame() {
        currentGameField.addOnPropertyChangedCallback(callback);
    }

    private OnItemClicked onItemClicked = p -> showAddPlayerPointsDialog(Collections.singletonList(adapter.getPlayer(p)));

    private void initGamePlayers() {
        adapter = new PlayerGameRecyclerViewAdapter(new ArrayList<>(), mContext, onItemClicked);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);

        SlideInDownAnimator animator = new SlideInDownAnimator();
        animator.setAddDuration(1000);
        animator.setRemoveDuration(1000);

        gamePlayers.setAdapter(adapter);
        gamePlayers.setLayoutManager(linearLayoutManager);
        gamePlayers.setItemAnimator(animator);
    }

    private View.OnClickListener onClickListener = v -> {
        initNewCurrentGame(choicePlayersDialog.getChosenPlayers());
        choicePlayersDialog.dismiss();
    };

    private void initChoiceDialog() {
        choicePlayersDialog = new ChoicePlayersDialog(mContext, new ArrayList<>(), onClickListener);
    }

    private void saveGameResultAndFinish() {
        if (currentGameField.get() != null) {
            List<GameHistory> gameHistories = Objects.requireNonNull(currentGameField.get()).finish();

            compositeDisposable.add(
                    gameHistoryDao.insertAll(gameHistories)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    l -> startNewCurrentGame(),
                                    this::saveGameResultError
                            )
            );

            adapter.clear();
            changeGameButton(false);
        }
    }

    private void saveGameResultError(Throwable e) {
        if (e instanceof SQLiteConstraintException) {
            showToast(R.string.save_game_player_does_not_exist);
        } else {
            showToast(R.string.unexpected_error);
        }
    }

    private void startNewCurrentGame() {
        if (currentGameField.get() != null) {
            setCurrentGameGameId();
            adapter.setPlayers(Objects.requireNonNull(currentGameField.get()).getPlayers());
            changeGameButton(true);
        }
    }

    private void initNewCurrentGame(List<Player> players) {
        currentGameField.set(new CurrentGame(players));
    }

    private void setCurrentGameGameId() {
        if (currentGameField.get() != null && currentGameField.get().getGameId() == 0) {
            Game game = new Game();
            game.setLocalDate(new Date());

            compositeDisposable.add(
                    gameDao.insert(game)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    i -> {
                                        if (currentGameField.get() != null) {
                                            currentGameField.get().setGameId(i);
                                        }
                                    },
                                    e -> obtainGameIdFailed()
                            )
            );
        }

    }

    private void obtainGameIdFailed() {
        showToast(R.string.set_up_game_failed);
        currentGameField.set(new CurrentGame());
    }


    private void loadPlayersFailed() {
        showToast(R.string.load_player_failed);
    }

    private void showToast(int resId) {
        Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).show();
    }

    private void loadPlayers() {
        compositeDisposable.add(
                playerDao.getAll()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                this::setDialogPlayers,
                                e -> loadPlayersFailed())
        );
    }

    private void setDialogPlayers(List<Player> players) {
        choicePlayersDialog.setPlayers(players);
    }


    private void showAddPlayerPointsDialog(List<PlayerWithPoints> playersWithPoints) {
        AddPlayerPointsDialog addPlayerPointsDialog = new AddPlayerPointsDialog(mContext, playersWithPoints);

        addPlayerPointsDialog.show();
    }

    private void startSpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            startActivityForResult(intent, SPEECH_REQUEST_CODE);
        } else {
            showToast(R.string.speech_recognition_not_supported);
        }

    }

    private void resolvePlayerPointsSpeech(List<String> results) {
        if (currentGameField.get() != null && !results.isEmpty()) {
            PlayerPointsSpeechService service = new PlayerPointsSpeechService(Objects.requireNonNull(currentGameField.get()).getPlayers());

            List<String> firstResults = Arrays.asList(results.get(0).split("\\s+"));

            service.addPlayersPoints(firstResults);
        }
    }

    private void changeGameButton(boolean enable) {
        endRound.setEnabled(enable);
        finish.setEnabled(enable);
        pointsBySpeech.setEnabled(enable);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SPEECH_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                resolvePlayerPointsSpeech(results);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
