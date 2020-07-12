package com.rolnik.remik.repositories;

import com.rolnik.remik.daos.GameHistoryDao;
import com.rolnik.remik.model.GameHistory;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;

@AllArgsConstructor

public class GameHistoryRepository {
    private final GameHistoryDao gameHistoryDao;


    Maybe<Long> insert(final GameHistory gameHistory){
        return gameHistoryDao.insert(gameHistory);
    }

    Maybe<List<Long>> insertAll(final List<GameHistory> gameHistories){
        return gameHistoryDao.insertAll(gameHistories);
    }

    Flowable<List<GameHistory>> getAll(){
        return gameHistoryDao.getAll();
    }

}

