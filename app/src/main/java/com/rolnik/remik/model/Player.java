package com.rolnik.remik.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.rolnik.remik.BR;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity(tableName = "players",
        indices = {@Index(value = "nickname", unique = true)})
public class Player extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String nickname;
    private String firstName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Bindable
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        notifyPropertyChanged(BR.nickname);
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

}
