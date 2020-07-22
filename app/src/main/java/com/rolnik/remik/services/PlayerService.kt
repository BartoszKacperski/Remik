package com.rolnik.remik.services

import com.rolnik.remik.mappers.PlayerRequestMapper
import com.rolnik.remik.mappers.PlayerResponseMapper
import com.rolnik.remik.model.Player
import com.rolnik.remik.restdao.PlayerRestDao
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class PlayerService @Inject constructor(
        val playerRestDao: PlayerRestDao,
        val ioThread: Scheduler,
        var mainThread: Scheduler,
        val playerResponseMapper: PlayerResponseMapper = PlayerResponseMapper(),
        val playerRequestMapper: PlayerRequestMapper = PlayerRequestMapper()
) {


    fun getAll(): Single<MutableList<Player>> = playerRestDao.getAll()
            .subscribeOn(ioThread)
            .observeOn(mainThread)
            .map { playerResponseMapper.mapListFrom(it) }

    fun create(player: Player): Single<Player> = playerRestDao.create(
            playerRequestMapper.mapTo(player)
    )
            .subscribeOn(ioThread)
            .observeOn(mainThread)
            .map { playerResponseMapper.mapFrom(it) }

    fun delete(player : Player) : Completable = playerRestDao.delete(player.id)
            .subscribeOn(ioThread)
            .observeOn(mainThread)
}