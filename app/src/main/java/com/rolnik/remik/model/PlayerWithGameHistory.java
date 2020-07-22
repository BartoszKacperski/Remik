package com.rolnik.remik.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;


public class PlayerWithGameHistory {
    @Embedded
    private Player player;

    @Relation(parentColumn = "id", entityColumn = "playerId", entity = GameHistory.class)
    List<GameHistory> gameHistories;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<GameHistory> getGameHistories() {
        return gameHistories;
    }

    public void setGameHistories(List<GameHistory> gameHistories) {
        this.gameHistories = gameHistories;
    }
}
