package com.rolnik.remik.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class GameWithGameHistory {
    @Embedded
    private Game game;

    @Relation(parentColumn = "id", entityColumn = "gameId", entity = GameHistory.class)
    Set<GameHistory> gameHistories;
}
