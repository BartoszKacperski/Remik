package com.rolnik.remik.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.rolnik.remik.BR;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

public class PlayerWithChoice extends BaseObservable {
    private Player player;
    private boolean isChecked;

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
