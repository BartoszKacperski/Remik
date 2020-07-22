package com.rolnik.remik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.rolnik.remik.databinding.PlayerDetailsBinding;
import com.rolnik.remik.model.Player;
import com.rolnik.remik.model.PlayerWithGameHistory;
import com.rolnik.remik.utils.OnItemClicked;
import com.rolnik.remik.viewholders.PlayerDetailsViewHolder;

import java.util.List;




public class PlayerRecyclerViewAdapter extends RecyclerView.Adapter<PlayerDetailsViewHolder> {
    private final List<Player> players;
    private final Context context;
    private final OnItemClicked onItemClicked;

    public PlayerRecyclerViewAdapter(List<Player> players, Context context, OnItemClicked onItemClicked) {
        this.players = players;
        this.context = context;
        this.onItemClicked = onItemClicked;
    }

    @NonNull
    @Override
    public PlayerDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        PlayerDetailsBinding playerDetailsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.player_details, parent, false);

        return new PlayerDetailsViewHolder(playerDetailsBinding, onItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerDetailsViewHolder holder, int position) {
        Player player = players.get(position);

        holder.bind(player);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setPlayerWithGameHistories(List<Player> players){
        this.players.clear();
        this.players.addAll(players);
        notifyDataSetChanged();
    }

    public Player getPlayer(int position){
        return players.get(position);
    }

    public void add(final Player player) {
        this.players.add(player);
        notifyItemInserted(players.size() - 1);
    }

    public void remove (int position) {
        this.players.remove(position);
        notifyItemRemoved(position);
    }

}
