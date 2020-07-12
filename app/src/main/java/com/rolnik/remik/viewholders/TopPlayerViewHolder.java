package com.rolnik.remik.viewholders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.rolnik.remik.databinding.TopPlayerBinding;
import com.rolnik.remik.model.PlayerWithGameHistory;
import com.rolnik.remik.utils.TopPlayerChartInitializer;

public class TopPlayerViewHolder extends RecyclerView.ViewHolder {
    private final TopPlayerBinding binding;
    private final Context context;
    private final ViewGroup transitionGroup;
    private final TopPlayerChartInitializer chartInitializer;


    public TopPlayerViewHolder(TopPlayerBinding binding, Context context, ViewGroup transitionGroup) {
        super(binding.getRoot());
        this.binding = binding;
        this.context = context;
        this.transitionGroup = transitionGroup;
        this.chartInitializer = new TopPlayerChartInitializer(context, binding.chart);

        this.binding.getRoot().setOnClickListener(v -> setChartVisibility());
    }

    public void bind(final PlayerWithGameHistory playerWithGameHistory) {
        binding.setPlayerDetails(playerWithGameHistory);
        chartInitializer.init(playerWithGameHistory.getGameHistories());
    }

    public void setBackgroundColor(int resId) {
        binding.root.setCardBackgroundColor(resId);
    }

    private void setChartVisibility() {
        if(!binding.chart.getData().isEmpty()){
            int visibility = binding.chart.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;

            TransitionManager.beginDelayedTransition(transitionGroup);

            binding.chart.setVisibility(visibility);

            if(visibility == View.VISIBLE){
                binding.chart.show(chartInitializer.getShowAnimation());
            }
        }
    }


}
