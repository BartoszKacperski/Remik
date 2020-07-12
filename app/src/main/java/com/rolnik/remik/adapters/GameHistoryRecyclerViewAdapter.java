package com.rolnik.remik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.rolnik.remik.model.Player;
import com.rolnik.remik.viewholders.GameHistoryViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GameHistoryRecyclerViewAdapter extends RecyclerView.Adapter<GameHistoryViewHolder> {
    private final Map<Player, Integer> playerIntegerHashMap;
    private final List<Player> playerList;
    private final Context context;

    public GameHistoryRecyclerViewAdapter(Map<Player, Integer> playerIntegerHashMap, Context context) {
        this.playerIntegerHashMap = playerIntegerHashMap;
        this.playerList = new ArrayList<>(playerIntegerHashMap.keySet());
        this.context = context;
    }

    @NonNull
    @Override
    public GameHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.game_history, parent, false);

        return new GameHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameHistoryViewHolder holder, int position) {
        Player player = playerList.get(position);

        holder.bind(player, playerIntegerHashMap.get(player));
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }
}
