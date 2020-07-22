package com.rolnik.remik.modules;

import android.content.Context;

import androidx.room.Room;

import com.rolnik.remik.R;
import com.rolnik.remik.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;



@Module
public class DatabaseModule {
    private final Context context;

    public DatabaseModule(Context context) {
        this.context = context;
    }

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
