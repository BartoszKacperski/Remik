package com.rolnik.remik.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.rolnik.remik.MyApplication;
import com.rolnik.remik.R;
import com.rolnik.remik.adapters.PlayerRecyclerViewAdapter;
import com.rolnik.remik.dialogs.AddPlayerDialog;
import com.rolnik.remik.model.Player;
import com.rolnik.remik.services.PlayerService;
import com.rolnik.remik.utils.OnItemClicked;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PlayerFragments extends Fragment {

    @BindView(R.id.players)
    RecyclerView players;

    @Inject
    PlayerService playerService;

    private CompositeDisposable compositeDisposable;
    private AddPlayerDialog addPlayerDialog;
    private PlayerRecyclerViewAdapter playersAdapter;
    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.players_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        MyApplication.app().databaseComponent().inject(this);

        compositeDisposable = new CompositeDisposable();

        setUpPlayerRecyclerView();
        loadPlayers();
    }

    @OnClick(R.id.addPlayer)
    public void showAddDialog() {
        addPlayerDialog = new AddPlayerDialog(mContext, onClickListener);
        addPlayerDialog.show();
    }

    private View.OnClickListener onClickListener = v -> {
        Player player = addPlayerDialog.getPlayer();

        addPlayerDialog.dismiss();

        insertPlayer(player);
    };

    private void insertPlayer(final Player player) {
        compositeDisposable.add(playerService.create(player).subscribe(
                this::insertPlayerSuccess,
                this::insertPlayerFailed
        ));
    }

    private void insertPlayerSuccess(final Player player) {
        showToast(R.string.add_player_successfull);
        playersAdapter.add(player);
    }

    private void insertPlayerFailed(Throwable e) {
        if (e instanceof SQLiteConstraintException) {
            showToast(R.string.player_nickname_exists);
        } else {
            showToast(R.string.add_player_fail);
        }
    }

    private void showToast(int resId) {
        Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).show();
    }

    private OnItemClicked onItemClicked = p -> {
        Player player = playersAdapter.getPlayer(p);

        deletePlayer(player, p);
    };

    private void setUpPlayerRecyclerView() {
        playersAdapter = new PlayerRecyclerViewAdapter(new ArrayList<>(), mContext, onItemClicked);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        players.setAdapter(playersAdapter);
        players.setLayoutManager(staggeredGridLayoutManager);
    }

    private void loadPlayers() {
        compositeDisposable.add(
                playerService.getAll()
                        .subscribe(
                                this::fillPlayerChooserAdapter,
                                e -> loadPlayersFailed()
                        ));
    }

    private void deletePlayer(Player player, int position) {
        compositeDisposable.add(
                playerService.delete(player)
                        .subscribe(
                                () -> deletePlayerSuccess(position),
                                e -> deletePlayerFailed()
                        )
        );
    }

    private void deletePlayerFailed() {
        showToast(R.string.remove_player_failed);
    }

    private void deletePlayerSuccess(int position) {
        playersAdapter.remove(position);
    }

    private void loadPlayersFailed() {
        showToast(R.string.load_player_failed);
    }

    private void fillPlayerChooserAdapter(final List<Player> players) {
        playersAdapter.setPlayerWithGameHistories(players);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

}
