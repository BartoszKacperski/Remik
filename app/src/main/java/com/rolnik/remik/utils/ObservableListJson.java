package com.rolnik.remik.utils;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ObservableListJson implements JsonSerializer<ObservableList<Integer>>, JsonDeserializer<ObservableList<Integer>> {
    @Override
    public ObservableList<Integer> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ObservableList<Integer> observableList = new ObservableArrayList<>();

        if(json.isJsonArray()){
            JsonArray jsonArray = json.getAsJsonArray();

            for(JsonElement jsonElement : jsonArray){
                observableList.add(jsonElement.getAsInt());
            }
        }

        return observableList;
    }

    @Override
    public JsonElement serialize(ObservableList<Integer> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray jsonArray = new JsonArray();

        for(Integer value : src){
            jsonArray.add(value);
        }

        return jsonArray;
    }
}
