package com.rolnik.remik.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.databinding.PlayerWithCheckboxBinding;
import com.rolnik.remik.model.PlayerWithChoice;

public class PlayerWithCheckBoxViewHolder extends RecyclerView.ViewHolder {
    private PlayerWithCheckboxBinding binding;

    public PlayerWithCheckBoxViewHolder(PlayerWithCheckboxBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


    public void bind(final PlayerWithChoice player) {
        binding.setPlayerWithChoice(player);
    }
}
