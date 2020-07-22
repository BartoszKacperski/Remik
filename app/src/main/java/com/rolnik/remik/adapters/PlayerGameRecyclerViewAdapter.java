package com.rolnik.remik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.rolnik.remik.databinding.PlayerGameBinding;
import com.rolnik.remik.model.PlayerWithPoints;
import com.rolnik.remik.utils.OnItemClicked;
import com.rolnik.remik.viewholders.PlayerGameViewHolder;

import java.util.List;


public class PlayerGameRecyclerViewAdapter extends RecyclerView.Adapter<PlayerGameViewHolder> {
    private final List<PlayerWithPoints> playerWithPoints;
    private final Context context;
    private final OnItemClicked onItemClicked;

    public PlayerGameRecyclerViewAdapter(List<PlayerWithPoints> playerWithPoints, Context context, OnItemClicked onItemClicked) {
        this.playerWithPoints = playerWithPoints;
        this.context = context;
        this.onItemClicked = onItemClicked;
    }

    @NonNull
    @Override
    public PlayerGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        PlayerGameBinding binding = DataBindingUtil.inflate(inflater, R.layout.player_game, parent, false);

        return new PlayerGameViewHolder(binding, onItemClicked, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerGameViewHolder holder, int position) {
        PlayerWithPoints player = playerWithPoints.get(position);

        holder.bind(player);
    }

    @Override
    public int getItemCount() {
        return playerWithPoints.size();
    }

    public PlayerWithPoints getPlayer(int position){
        return playerWithPoints.get(position);
    }

    public void setPlayers(List<PlayerWithPoints> players){
        this.clear();

        if(players != null){
            this.playerWithPoints.addAll(players);
            notifyItemRangeInserted(0, players.size());
        }
    }

    public void clear(){
        int size = playerWithPoints.size();

        this.playerWithPoints.clear();

        notifyItemRangeRemoved(0, size);
    }
}
