package uz.pdp.student.jahongir.sevgisherlariapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    private static MySharedPreference mySharedPreference = new MySharedPreference();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static String FILE_NAME = "PHONE_APP";

    public static MySharedPreference getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return mySharedPreference;
    }

    public void setString(String str) {
        editor.putString("number", str).commit();
    }

    public String getString() {
        return sharedPreferences.getString("number", "");
    }

    public void setCount(String count) {
        editor.putString("count_key", count);
    }

    public String getCount() {
        return sharedPreferences.getString("count_key", "");
    }

    public void removeString() {
        editor.putString("number","").commit();
    }

    public void clearDate() {
        editor.clear().commit();
    }
}
