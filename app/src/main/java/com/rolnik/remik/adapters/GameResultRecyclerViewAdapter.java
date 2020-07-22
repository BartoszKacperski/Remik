package com.rolnik.remik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.rolnik.remik.databinding.GameResultBinding;
import com.rolnik.remik.model.PlayerWithPoints;
import com.rolnik.remik.viewholders.GameResultViewHolder;

import java.util.List;


public class GameResultRecyclerViewAdapter extends RecyclerView.Adapter<GameResultViewHolder> {
    private final Context context;
    private final List<PlayerWithPoints> playerWithPointsList;

    public GameResultRecyclerViewAdapter(Context context, List<PlayerWithPoints> playerWithPointsList) {
        this.context = context;
        this.playerWithPointsList = playerWithPointsList;
    }

    @NonNull
    @Override
    public GameResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        GameResultBinding gameResultBinding = DataBindingUtil.inflate(layoutInflater, R.layout.game_result, parent, false);

        return new GameResultViewHolder(gameResultBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GameResultViewHolder holder, int position) {
        PlayerWithPoints playerWithPoints = playerWithPointsList.get(position);

        holder.bind(playerWithPoints);

        if (position == 0) {
            holder.setBackroundColor(context.getResources().getColor(R.color.gold));
        } else if (position == 1) {
            holder.setBackroundColor(context.getResources().getColor(R.color.silver));
        } else if (position == 2) {
            holder.setBackroundColor(context.getResources().getColor(R.color.bronze));
        }
    }

    @Override
    public int getItemCount() {
        return playerWithPointsList.size();
    }
}
