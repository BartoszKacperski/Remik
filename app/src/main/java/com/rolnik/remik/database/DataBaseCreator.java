package com.rolnik.remik.database;

import android.content.Context;

import androidx.room.Room;

import com.rolnik.remik.R;

public class DataBaseCreator {

    private DataBaseCreator(){

    }

    private static volatile AppDatabase INSTANCE = null;

    public static AppDatabase getDatabaseInstance(Context context){
        if(INSTANCE == null){
            synchronized (DataBaseCreator.class){
                if(INSTANCE == null){
                    INSTANCE = createInstance(context);
                }
            }
        }

        return INSTANCE;
    }

    private static AppDatabase createInstance(Context context) {
        return Room.
                databaseBuilder(
                        context,
                        AppDatabase.class,
                        context.getString(R.string.database_name))
                .enableMultiInstanceInvalidation()
                .build();
    }
}
