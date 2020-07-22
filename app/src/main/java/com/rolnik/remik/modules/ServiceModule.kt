package com.rolnik.remik.modules

import com.rolnik.remik.restdao.PlayerRestDao
import com.rolnik.remik.services.PlayerService
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RestDaoModule::class])
class ServiceModule {
    @Provides
    @Singleton
    fun providePlayerService(
            playerRestDao: PlayerRestDao,
            @Named("ioThread")
            ioThread: Scheduler,
            @Named("mainThread")
            mainThread: Scheduler
    ): PlayerService = PlayerService(
            playerRestDao,
            ioThread,
            mainThread
    )
}