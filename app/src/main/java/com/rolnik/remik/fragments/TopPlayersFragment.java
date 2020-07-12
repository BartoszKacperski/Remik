package com.rolnik.remik.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.MyApplication;
import com.rolnik.remik.R;
import com.rolnik.remik.adapters.TopPlayerRecyclerViewAdapter;
import com.rolnik.remik.daos.PlayerDao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TopPlayersFragment extends Fragment {
    @BindView(R.id.podium)
    ImageView podium;
    @BindView(R.id.topPlayers)
    RecyclerView topPlayers;
    @BindView(R.id.root)
    CoordinatorLayout root;

    private Context mContext;
    private TopPlayerRecyclerViewAdapter adapter;
    private Disposable disposable;

    @Inject
    PlayerDao playerDao;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.top_players_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        MyApplication.app().databaseComponent().inject(this);

        initTopPlayers();
        animatePodiumIcon();
        animateTopPlayers();
        loadPlayers();
    }

    private void animatePodiumIcon() {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.bounce_from_top);

        animation.setStartOffset(250);
        animation.setDuration(2000);

        podium.startAnimation(animation);
    }

    private void animateTopPlayers(){
        Animation animation = new AlphaAnimation(0, 1.f);

        animation.setDuration(2000);

        topPlayers.startAnimation(animation);
    }

    private void initTopPlayers() {
        adapter = new TopPlayerRecyclerViewAdapter(mContext, new ArrayList<>(), root);

        topPlayers.setAdapter(adapter);
    }

    private void loadPlayers(){
        disposable = playerDao.getAllWithGameHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::setAndSort);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (disposable != null) {
            disposable.dispose();
        }
    }
}
