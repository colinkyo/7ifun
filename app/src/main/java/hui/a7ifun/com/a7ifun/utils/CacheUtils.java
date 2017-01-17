package hui.a7ifun.com.a7ifun.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 7yan on 2017/1/8.
 */

public class CacheUtils
{
    /**
     *
     * @param context
     * @param key
     * @param values
     */
    public static void putString(Context context,String key,String values){
        SharedPreferences sharedPreferences = context.getSharedPreferences("7Yan",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,values).commit();
    }

    /**
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context,String key)
{
    SharedPreferences sharedPreferences =context.getSharedPreferences("7Yan",Context.MODE_PRIVATE);
    return sharedPreferences.getString(key,"");
}
}
