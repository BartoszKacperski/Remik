package com.rolnik.remik.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.rolnik.remik.R;
import com.rolnik.remik.model.CurrentGame;

public class CurrentGameSaveState {
    private final SharedPreferences sharedPreferences;
    private final Context context;

    public CurrentGameSaveState(Context context){
        this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.shared_pref), Context.MODE_PRIVATE);
        this.context = context;
    }

    public void save(CurrentGame currentGame){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(currentGame);

        editor.putString(context.getString(R.string.current_game), json);

        editor.apply();
    }

    public CurrentGame restoreOrNewGame(){
        CurrentGame currentGame = new CurrentGame();

        if (sharedPreferences.contains(context.getString(R.string.current_game))) {
            Gson gson = new Gson();
            String json = sharedPreferences
                    .getString(
                            context.getString(R.string.current_game)
                            , ""
                    );

            if (!(json == null || json.isEmpty())) {
                currentGame = gson.fromJson(json, CurrentGame.class);
            }
        }

        removeStateFromSharedPreferences();

        return currentGame;
    }

    private void removeStateFromSharedPreferences(){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(context.getString(R.string.current_game));

        editor.apply();
    }
}
