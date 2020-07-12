package com.rolnik.remik.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.rolnik.remik.model.Player;
import com.rolnik.remik.model.PlayerWithGameHistory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public interface PlayerDao {
    @Insert
    Maybe<Long> insert(Player player);

    @Query("SELECT * FROM PLAYERS")
    Observable<List<Player>> getAll();

    @Delete
    Completable delete(Player player);

    @Transaction
    @Query("SELECT * FROM PLAYERS")
    Observable<List<PlayerWithGameHistory>> getAllWithGameHistory();
}
