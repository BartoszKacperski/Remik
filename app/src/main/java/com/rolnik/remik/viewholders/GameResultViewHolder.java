package com.rolnik.remik.viewholders;

import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.rolnik.remik.databinding.GameResultBinding;
import com.rolnik.remik.model.PlayerWithPoints;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameResultViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.root)
    LinearLayout root;


    private GameResultBinding binding;

    public GameResultViewHolder(@NonNull GameResultBinding binding) {
        super(binding.getRoot());
        ButterKnife.bind(this, binding.getRoot());
        this.binding = binding;
    }

    public void bind(PlayerWithPoints playerWithPoints) {
        binding.setPlayer(playerWithPoints);
    }

    public void setBackroundColor(int color) {
        root.setBackgroundColor(color);
    }
}
