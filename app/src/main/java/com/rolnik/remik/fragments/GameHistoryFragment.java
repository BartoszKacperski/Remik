package com.rolnik.remik.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.MyApplication;
import com.rolnik.remik.R;
import com.rolnik.remik.adapters.GameRecyclerViewAdapter;
import com.rolnik.remik.daos.GameDao;
import com.rolnik.remik.daos.GameHistoryDao;
import com.rolnik.remik.daos.PlayerDao;
import com.rolnik.remik.model.Game;
import com.rolnik.remik.utils.OnGameDeleteButtonClicked;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GameHistoryFragment extends Fragment {
    @BindView(R.id.games)
    RecyclerView games;

    private Context mContext;
    private GameRecyclerViewAdapter adapter;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    PlayerDao playerDao;
    @Inject
    GameDao gameDao;
    @Inject
    GameHistoryDao gameHistoryDao;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_history_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        MyApplication.app().databaseComponent().inject(this);
        setUpGames();
    }

    private OnGameDeleteButtonClicked buttonClicked = new OnGameDeleteButtonClicked() {
        @Override
        public void onClick(int position) {
            Game game = adapter.get(position);

            disposable.add(Completable.mergeArray(gameHistoryDao.delete(game.getId()), gameDao.delete(game))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> adapter.remove(position)));
        }
    };

    private void setUpGames() {
        adapter = new GameRecyclerViewAdapter(mContext, new ArrayList<>(), new ArrayList<>(), buttonClicked);
        games.setAdapter(adapter);
        games.setItemAnimator(null);

        disposable.add(Observable.zip(playerDao.getAllWithGameHistory(), gameDao.getAll(), Pair::new)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(p -> adapter.changeData(p.second, p.first)));
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if(disposable != null) {
            disposable.dispose();
            disposable = new CompositeDisposable();
        }
    }
}
