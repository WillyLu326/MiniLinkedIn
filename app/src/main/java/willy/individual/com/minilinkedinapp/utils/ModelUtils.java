package willy.individual.com.minilinkedinapp.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * Created by zhenglu on 2/21/17.
 */

public class ModelUtils {

    private static Gson gson = new Gson();

    private static final String SP_KEY = "model";

    public static void saveModel(Context context, String key, Object model) {
        SharedPreferences sp = context.getSharedPreferences(SP_KEY, context.MODE_PRIVATE);
        sp.edit().putString(key, gson.toJson(model)).apply();
    }

    public static <T> T readModel(Context context, String key, TypeToken<T> typeToken) {
        SharedPreferences sp = context.getSharedPreferences(SP_KEY, context.MODE_PRIVATE);
        String jsonString = sp.getString(key, "");
        return gson.fromJson(jsonString, typeToken.getType());
    }
}
