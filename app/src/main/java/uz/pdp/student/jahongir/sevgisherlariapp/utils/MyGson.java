package uz.pdp.student.jahongir.sevgisherlariapp.utils;

import com.google.gson.Gson;

public class MyGson {
    private static MyGson myGson = new MyGson();
    private static Gson gson;

    public static MyGson getInstance() {
        if (gson == null){
            gson = new Gson();
        }
        return myGson;
    }

    public Gson getGson() {
        return gson;
    }
}
