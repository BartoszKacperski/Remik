package com.rolnik.remik.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.rolnik.remik.BR;

public class PlayerWithChoice extends BaseObservable {
    private Player player;
    private boolean isChecked;

    public PlayerWithChoice(Player player, boolean isChecked) {
        this.player = player;
        this.isChecked = isChecked;
    }

    public PlayerWithChoice() {
    }

    @Bindable
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        notifyPropertyChanged(BR.checked);
    }

    public Player getPlayer() {
        return player;
    }
}
