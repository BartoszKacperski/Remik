package com.rolnik.remik.modules

import com.rolnik.remik.restdao.PlayerRestDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class RestDaoModule {
    @Singleton
    @Provides
    fun provideUserDao(retrofit: Retrofit): PlayerRestDao {
        return retrofit.create(PlayerRestDao::class.java)
    }
}