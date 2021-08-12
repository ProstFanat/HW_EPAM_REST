package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonBuilder {
    public static String parseToJson(Object object){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(object);
    }
}
