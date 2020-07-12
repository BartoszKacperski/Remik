package com.rolnik.remik.utils;

import androidx.databinding.ObservableList;

public abstract class OnListChanged extends ObservableList.OnListChangedCallback<ObservableList<Integer>> {
    public abstract void onChange(ObservableList<Integer> sender);

    @Override
    public void onChanged(ObservableList<Integer> sender) {
        onChange(sender);
    }

    @Override
    public void onItemRangeChanged(ObservableList<Integer> sender, int positionStart, int itemCount) {
        onChange(sender);
    }

    @Override
    public void onItemRangeInserted(ObservableList<Integer> sender, int positionStart, int itemCount) {
        onChange(sender);
    }

    @Override
    public void onItemRangeMoved(ObservableList<Integer> sender, int fromPosition, int toPosition, int itemCount) {
        onChange(sender);
    }

    @Override
    public void onItemRangeRemoved(ObservableList<Integer> sender, int positionStart, int itemCount) {
        onChange(sender);
    }
}
