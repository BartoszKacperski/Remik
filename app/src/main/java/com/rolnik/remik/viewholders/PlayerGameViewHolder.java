package com.rolnik.remik.viewholders;

import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.adapters.PointsRecyclerViewAdapter;
import com.rolnik.remik.databinding.PlayerGameBinding;
import com.rolnik.remik.model.PlayerWithPoints;
import com.rolnik.remik.utils.OnItemClicked;

public class PlayerGameViewHolder extends RecyclerView.ViewHolder {
    private final PlayerGameBinding binding;
    private final ViewGroup root;

    public PlayerGameViewHolder(PlayerGameBinding binding, OnItemClicked onItemClicked, ViewGroup root) {
        super(binding.getRoot());
        this.binding = binding;
        this.root = root;

        this.binding.root.setOnClickListener(view -> onItemClicked.onClick(getAdapterPosition()));
        this.binding.expand.setOnClickListener(view -> expandOrHide());
    }

    public void bind(PlayerWithPoints playerWithPoints) {
        binding.setPlayer(playerWithPoints);
        binding.points.setAdapter(getAdapter(playerWithPoints));
    }

    private void expandOrHide(){
        int visibility = binding.points.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
        int resourceId = visibility == View.VISIBLE ? android.R.drawable.arrow_up_float : android.R.drawable.arrow_down_float;

        TransitionManager.beginDelayedTransition(root);

        binding.points.setVisibility(visibility);
        binding.expand.setImageResource(resourceId);
    }

    private PointsRecyclerViewAdapter getAdapter(PlayerWithPoints playerWithPoints) {
        return new PointsRecyclerViewAdapter(playerWithPoints.getCurrentPoints(), binding.getRoot().getContext());
    }
}
