package com.rolnik.remik.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class SchedulersModule {
    @Provides
    @Named("mainThread")
    fun provideMainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Named("ioThread")
    fun provideIoThread(): Scheduler {
        return Schedulers.io()
    }
}