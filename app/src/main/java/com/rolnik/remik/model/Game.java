package com.rolnik.remik.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.rolnik.remik.BR;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Entity(tableName = "games")
public class Game extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private Date localDate;

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(com.rolnik.remik.BR.id);
    }

    @Bindable
    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
        notifyPropertyChanged(com.rolnik.remik.BR.localDate);
    }
}
