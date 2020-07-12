package com.rolnik.remik.modules;

import com.rolnik.remik.daos.GameDao;
import com.rolnik.remik.daos.GameHistoryDao;
import com.rolnik.remik.daos.PlayerDao;
import com.rolnik.remik.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaosModule {

    @Provides
    @Singleton
    public PlayerDao providePlayerDao(AppDatabase appDatabase){
        return appDatabase.playerDao();
    }

    @Provides
    @Singleton
    public GameDao provideGameDao(AppDatabase appDatabase){
        return appDatabase.gameDao();
    }

    @Provides
    @Singleton
    public GameHistoryDao provideGameHistoryDao(AppDatabase appDatabase){
        return appDatabase.gameHistoryDao();
    }
}
