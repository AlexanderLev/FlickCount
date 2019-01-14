package pro.kbgame.flickcount.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import pro.kbgame.flickcount.model.Flick;

public class JSONHelper {
    private static JSONHelper instance;


    public String getArrayInJsonString(ArrayList<Flick> flicks) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(flicks);
    }

    public ArrayList<Flick> getFlickArray(String dataString) {
        Gson g = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        Type listType = new TypeToken<ArrayList<Flick>>() {
        }.getType();
        return g.fromJson(dataString, listType);
    }

    public static JSONHelper getInstance() {
        if (instance == null) {
            instance = new JSONHelper();
        }
        return instance;
    }

}
