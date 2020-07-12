package com.rolnik.remik.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static androidx.room.ForeignKey.CASCADE;

@NoArgsConstructor
@AllArgsConstructor
@Data

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
}
