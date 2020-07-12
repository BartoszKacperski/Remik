package com.rolnik.remik.modules;

import android.content.Context;

import androidx.room.Room;

import com.rolnik.remik.R;
import com.rolnik.remik.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;

@AllArgsConstructor

@Module
public class DatabaseModule {
    private final Context context;

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase() {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                context.getString(R.string.database_name)
        ).build();
    }
}
