package com.rolnik.remik.modules;

import com.rolnik.remik.daos.GameDao;
import com.rolnik.remik.daos.GameHistoryDao;
import com.rolnik.remik.daos.PlayerDao;
import com.rolnik.remik.repositories.GameHistoryRepository;
import com.rolnik.remik.repositories.GameRepository;
import com.rolnik.remik.repositories.PlayerRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

    @Provides
    @Singleton
    public PlayerRepository providePlayerRepository(PlayerDao playerDao){
        return new PlayerRepository(playerDao);
    }

    @Provides
    @Singleton
    public GameRepository provideGameRepository(GameDao gameDao){
        return new GameRepository(gameDao);
    }

    @Provides
    @Singleton
    public GameHistoryRepository provideGameHistoryRepository(GameHistoryDao gameHistoryDao){
        return new GameHistoryRepository(gameHistoryDao);
    }
}
