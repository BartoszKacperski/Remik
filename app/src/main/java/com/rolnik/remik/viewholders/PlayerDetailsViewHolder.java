package com.rolnik.remik.viewholders;

import android.os.Handler;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.rolnik.remik.databinding.PlayerDetailsBinding;
import com.rolnik.remik.model.PlayerWithGameHistory;
import com.rolnik.remik.utils.OnItemClicked;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerDetailsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.sceneRoot)
    FrameLayout sceneRoot;

    ImageButton delete;

    private final PlayerDetailsBinding binding;
    private final OnItemClicked onItemClicked;
    private Scene firstScene;
    private Scene secondScene;

    private Runnable runnable = () -> TransitionManager.go(firstScene);

    public PlayerDetailsViewHolder(final PlayerDetailsBinding playerDetailsBinding, final OnItemClicked onItemClicked){
        super(playerDetailsBinding.getRoot());
        ButterKnife.bind(this, playerDetailsBinding.getRoot());

        this.binding = playerDetailsBinding;
        this.onItemClicked = onItemClicked;
        this.firstScene = Scene.getSceneForLayout(sceneRoot, R.layout.player_image, binding.getRoot().getContext());
        this.secondScene = Scene.getSceneForLayout(sceneRoot, R.layout.player_delete, binding.getRoot().getContext());

        binding.sceneRoot.setOnClickListener( v -> {
            TransitionManager.go(secondScene);
            delete = secondScene.getSceneRoot().findViewById(R.id.delete);
            delete.setOnClickListener( v1 -> onItemClicked.onClick(getAdapterPosition()));
            new Handler().postDelayed(runnable, 2500);
        });
    }



    public void bind(final PlayerWithGameHistory playerWithGameHistory){
        binding.setPlayerDetails(playerWithGameHistory);
    }
}
