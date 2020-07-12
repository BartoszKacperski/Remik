package com.rolnik.remik.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.annimon.stream.Stream;
import com.rolnik.remik.R;
import com.rolnik.remik.databinding.TopPlayerBinding;
import com.rolnik.remik.model.GameHistory;
import com.rolnik.remik.model.PlayerWithGameHistory;
import com.rolnik.remik.viewholders.TopPlayerViewHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;


public class TopPlayerRecyclerViewAdapter extends RecyclerView.Adapter<TopPlayerViewHolder> {
    private final Context context;
    private final List<PlayerWithGameHistory> playerWithGameHistories;
    private final ViewGroup transitionGroup;

    public TopPlayerRecyclerViewAdapter(Context context, List<PlayerWithGameHistory> playerWithGameHistories, ViewGroup transitionGroup) {
        this.context = context;
        this.playerWithGameHistories = playerWithGameHistories;
        this.transitionGroup = transitionGroup;
    }

    @NonNull
    @Override
    public TopPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        TopPlayerBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.top_player, parent, false);

        return new TopPlayerViewHolder(binding, context, transitionGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull TopPlayerViewHolder holder, int position) {
        PlayerWithGameHistory player = playerWithGameHistories.get(position);

        holder.bind(player);

        switchHolderBackgroundDependsOnPosition(holder, position);
    }


    @Override
    public int getItemCount() {
        return playerWithGameHistories.size();
    }

    public void setAndSort(final List<PlayerWithGameHistory> players){
        this.playerWithGameHistories.clear();
        this.playerWithGameHistories.addAll(players);
        sortPlayersByPoints();
        notifyDataSetChanged();
    }


    private void sortPlayersByPoints() {
        Collections.sort(playerWithGameHistories, (firstPlayer, secondPlayer) -> countAveragePoints(firstPlayer).compareTo(countAveragePoints(secondPlayer)));
    }

    private BigDecimal countAveragePoints(PlayerWithGameHistory playerWithGameHistory) {
        int pointsSum = Stream.of(playerWithGameHistory.getGameHistories())
                .mapToInt(GameHistory::getPoints)
                .sum();

        BigDecimal averagePlayerPoints = BigDecimal.ZERO;

        if (playerWithGameHistory.getGameHistories() != null && playerWithGameHistory.getGameHistories().size() > 0) {
            BigDecimal sum = BigDecimal.valueOf(pointsSum);
            BigDecimal gamesCount = BigDecimal.valueOf(playerWithGameHistory.getGameHistories().size());

            averagePlayerPoints = sum.divide(gamesCount, RoundingMode.HALF_UP);
        }

        return averagePlayerPoints;
    }

    private void switchHolderBackgroundDependsOnPosition(TopPlayerViewHolder holder, int position) {
        Resources resources = context.getResources();
        if(position == 0) {
            holder.setBackgroundColor(resources.getColor(R.color.gold));
        } else if( position == 1){
            holder.setBackgroundColor(resources.getColor(R.color.silver));
        } else if(position == 2){
            holder.setBackgroundColor(resources.getColor(R.color.bronze));
        } else {
            holder.setBackgroundColor(resources.getColor(R.color.blueberry));
        }
    }
}
