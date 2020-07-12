package com.rolnik.remik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.rolnik.remik.databinding.PlayerDetailsBinding;
import com.rolnik.remik.model.PlayerWithGameHistory;
import com.rolnik.remik.utils.OnItemClicked;
import com.rolnik.remik.viewholders.PlayerDetailsViewHolder;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class PlayerRecyclerViewAdapter extends RecyclerView.Adapter<PlayerDetailsViewHolder> {
    private final List<PlayerWithGameHistory> playerWithGameHistories;
    private final Context context;
    private final OnItemClicked onItemClicked;

    @NonNull
    @Override
    public PlayerDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        PlayerDetailsBinding playerDetailsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.player_details, parent, false);

        return new PlayerDetailsViewHolder(playerDetailsBinding, onItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerDetailsViewHolder holder, int position) {
        PlayerWithGameHistory playerWithGameHistory = playerWithGameHistories.get(position);

        holder.bind(playerWithGameHistory);
    }

    @Override
    public int getItemCount() {
        return playerWithGameHistories.size();
    }

    public void setPlayerWithGameHistories(List<PlayerWithGameHistory> playerWithGameHistories){
        this.playerWithGameHistories.clear();
        this.playerWithGameHistories.addAll(playerWithGameHistories);
        notifyDataSetChanged();
    }

    public PlayerWithGameHistory getPlayer(int position){
        return playerWithGameHistories.get(position);
    }

}
