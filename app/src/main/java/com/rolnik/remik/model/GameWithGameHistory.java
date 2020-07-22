package com.rolnik.remik.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.Set;



public class GameWithGameHistory {
    @Embedded
    private Game game;

    @Relation(parentColumn = "id", entityColumn = "gameId", entity = GameHistory.class)
    Set<GameHistory> gameHistories;

    public GameWithGameHistory(Game game, Set<GameHistory> gameHistories) {
        this.game = game;
        this.gameHistories = gameHistories;
    }

    public GameWithGameHistory() {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<GameHistory> getGameHistories() {
        return gameHistories;
    }

    public void setGameHistories(Set<GameHistory> gameHistories) {
        this.gameHistories = gameHistories;
    }
}
