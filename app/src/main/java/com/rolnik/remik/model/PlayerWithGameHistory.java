package com.rolnik.remik.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlayerWithGameHistory {
    @Embedded
    private Player player;

    @Relation(parentColumn = "id", entityColumn = "playerId", entity = GameHistory.class)
    List<GameHistory> gameHistories;
}
