package com.rolnik.remik.repositories;

import com.rolnik.remik.daos.GameDao;
import com.rolnik.remik.model.Game;
import com.rolnik.remik.model.GameWithGameHistory;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import lombok.AllArgsConstructor;

@AllArgsConstructor

public class GameRepository {
    private final GameDao gameDao;


    Maybe<Long> insert(final Game game){
        return gameDao.insert(game);
    }

    Observable<List<Game>> getAll(){
        return gameDao.getAll();
    }

    Flowable<List<Game>> getWithId(final int id){
        return gameDao.getWithId(id);
    }

    Flowable<GameWithGameHistory> getGameHistoryWithId(final int id){
        return gameDao.getGameHistoryWithId(id);
    }
}
