package com.rolnik.remik.database;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.util.Date;

public class DatabaseConverters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
