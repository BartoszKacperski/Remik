package com.rolnik.remik.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rolnik.remik.model.GameHistory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface GameHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Maybe<Long> insert(final GameHistory gameHistory);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Maybe<List<Long>> insertAll(final List<GameHistory> gameHistories);
    @Query("SELECT * FROM GAME_HISTORIES")
    Flowable<List<GameHistory>> getAll();
    @Query("DELETE FROM GAME_HISTORIES WHERE gameId = :gameId")
    Completable delete(long gameId);
}
