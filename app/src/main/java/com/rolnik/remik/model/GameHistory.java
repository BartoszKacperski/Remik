package com.rolnik.remik.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


import static androidx.room.ForeignKey.CASCADE;



@Entity(tableName = "game_histories",
        foreignKeys = {
                @ForeignKey(onDelete = CASCADE, entity = Player.class,
                        parentColumns = "id", childColumns = "playerId")},
                indices = {
                @Index("playerId"),
        })
public class GameHistory {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long playerId;
    private long gameId;
    private int points;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
