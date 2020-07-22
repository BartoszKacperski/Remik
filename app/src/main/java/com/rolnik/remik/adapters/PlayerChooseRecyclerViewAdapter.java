package com.rolnik.remik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.rolnik.remik.databinding.PlayerWithCheckboxBinding;
import com.rolnik.remik.model.PlayerWithChoice;
import com.rolnik.remik.viewholders.PlayerWithCheckBoxViewHolder;

import java.util.List;

public class PlayerChooseRecyclerViewAdapter extends RecyclerView.Adapter<PlayerWithCheckBoxViewHolder> {
    private final Context context;
    private final List<PlayerWithChoice> players;

    public PlayerChooseRecyclerViewAdapter(Context context, List<PlayerWithChoice> players) {
        this.context = context;
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerWithCheckBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        PlayerWithCheckboxBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.player_with_checkbox, parent, false);

        return new PlayerWithCheckBoxViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerWithCheckBoxViewHolder holder, int position) {
        PlayerWithChoice player = players.get(position);

        holder.bind(player);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public List<PlayerWithChoice> getPlayers(){
        return players;
    }

    public void setPlayers(List<PlayerWithChoice> players){
        this.players.clear();
        this.players.addAll(players);
        notifyDataSetChanged();
    }
}
