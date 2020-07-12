package com.rolnik.remik.database;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rolnik.remik.daos.GameDao;
import com.rolnik.remik.daos.GameHistoryDao;
import com.rolnik.remik.daos.PlayerDao;
import com.rolnik.remik.model.Game;
import com.rolnik.remik.model.GameHistory;
import com.rolnik.remik.model.Player;

@androidx.room.Database(entities = {
        Player.class,
        Game.class,
        GameHistory.class
} , version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();
    public abstract GameHistoryDao gameHistoryDao();
    public abstract GameDao gameDao();
}
