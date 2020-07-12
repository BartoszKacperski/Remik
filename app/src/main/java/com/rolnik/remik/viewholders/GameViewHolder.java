package com.rolnik.remik.viewholders;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.rolnik.remik.adapters.GameHistoryRecyclerViewAdapter;
import com.rolnik.remik.databinding.GameBinding;
import com.rolnik.remik.model.Game;
import com.rolnik.remik.model.Player;
import com.rolnik.remik.utils.OnGameDeleteButtonClicked;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameViewHolder extends RecyclerView.ViewHolder {
    public ViewGroup root;
    @BindView(R.id.date)
    public TextView date;
    @BindView(R.id.gameHistory)
    public RecyclerView gameHistory;
    @BindView(R.id.delete)
    ImageButton delete;

    private Context context;
    private GameBinding binding;
    private GameHistoryRecyclerViewAdapter adapter;
    private OnGameDeleteButtonClicked onGameDeleteButtonClicked;

    public GameViewHolder(@NonNull GameBinding binding, Context context, ViewGroup root, OnGameDeleteButtonClicked buttonClicked) {
        super(binding.getRoot());
        ButterKnife.bind(this, binding.getRoot());
        this.binding = binding;
        this.context = context;
        this.adapter = new GameHistoryRecyclerViewAdapter(new HashMap<>(), context);
        this.gameHistory.setAdapter(adapter);
        this.root = root;
        this.onGameDeleteButtonClicked = buttonClicked;
    }

    public void bind(Game game, HashMap<Player, Integer> playerGameHistoryHashMap) {
        this.adapter = new GameHistoryRecyclerViewAdapter(playerGameHistoryHashMap, context);
        this.gameHistory.setAdapter(adapter);
        binding.setGame(game);
        delete.setOnClickListener(v ->
                onGameDeleteButtonClicked.onClick(getAdapterPosition()
                ));
    }
}
