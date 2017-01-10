package com.subhrajyoti.news;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MyDeserializer implements JsonDeserializer<JokeModel> {

    @Override
    public JokeModel deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException{

        JsonElement content = je.getAsJsonObject().get("value");
        return new Gson().fromJson(content, JokeModel.class);

    }
}