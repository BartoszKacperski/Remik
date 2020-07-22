package com.rolnik.remik.model;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.google.gson.annotations.JsonAdapter;
import com.rolnik.remik.utils.ObservableListJson;


public class PlayerWithPoints {
    private Player player;
    @JsonAdapter(ObservableListJson.class)
    private ObservableList<Integer> currentPoints;

    public PlayerWithPoints(Player player){
        this.player = player;
        this.currentPoints = new ObservableArrayList<>();
    }

    public void addPoint(int point){
        currentPoints.add(point);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ObservableList<Integer> getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(ObservableList<Integer> currentPoints) {
        this.currentPoints = currentPoints;
    }
}
