package com.rolnik.remik.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.rolnik.remik.model.Game;
import com.rolnik.remik.model.GameWithGameHistory;
import com.rolnik.remik.model.Player;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public interface GameDao {
    @Insert
    Maybe<Long> insert(final Game game);
    @Query("SELECT * FROM GAMES")
    Observable<List<Game>> getAll();
    @Query("SELECT * FROM GAMES WHERE id = :id")
    Flowable<List<Game>> getWithId(final int id);
    @Transaction
    @Query("SELECT * FROM GAMES WHERE id = :id")
    Flowable<GameWithGameHistory> getGameHistoryWithId(final int id);
    @Delete
    Completable delete(Game game);
}
