package com.rolnik.remik.restdao

import com.rolnik.remik.dtos.PlayerRequest
import com.rolnik.remik.dtos.PlayerResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface PlayerRestDao {
    @GET("players")
    fun getAll(): Single<MutableList<PlayerResponse>>

    @POST("players")
    fun create(@Body playerRequest: PlayerRequest): Single<PlayerResponse>

    @DELETE("players/{id}")
    fun delete(@Path("id") id: Long): Completable
}