package com.rolnik.remik.repositories;

import com.rolnik.remik.daos.PlayerDao;
import com.rolnik.remik.model.Player;
import com.rolnik.remik.model.PlayerWithGameHistory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;


public class PlayerRepository {

    private final PlayerDao playerDao;

    @Inject
    public PlayerRepository(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public Maybe<Long> insert(final Player player){
        return playerDao.insert(player);
    }

    public Observable<List<Player>> getAll(){
        return playerDao.getAll();
    }

    public Completable delete(final Player player){
        return playerDao.delete(player);
    }

    public Observable<List<PlayerWithGameHistory>> getAllWithGameHistory() {
        return playerDao.getAllWithGameHistory();
    }
}
