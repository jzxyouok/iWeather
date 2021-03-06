
package com.guohui.weather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.guohui.weather.Config;
import com.guohui.weather.R;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * Created by Dikaros on 2016/5/22.
 */
public class Util {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        //px/sp =
        return (int) ((spValue - 0.5f) * fontScale);
    }


    /**
     * 通过drawable的名字获取其id（通过反射）
     *
     * @param name drawable名
     * @return id
     */
    public static int getDrawableByName(String name) {
        int id = -1;
        Class drawable = R.drawable.class;
        Field field = null;
        try {
            field = drawable.getField(name);
            id = field.getInt(field.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    /**
     * 获取星期数
     *
     * @param dayOfWeek
     * @return
     */
    public static String getWeekDay(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 0:
                return "星期六";
        }
        return null;
    }

    /**
     * 设置preference
     *
     * @param context
     * @param key
     * @param token
     */
    public static void setPreference(Context context, String key, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Config.APP_ID,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, token);
        editor.commit();

    }

    public static void setPreferenceSet(Context context, String key, Set<String> set){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Config.APP_ID,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key,set);
        editor.commit();
    }

    public static Set<String> getPreferenceSet(Context context, String key){
        return context
                .getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE).getStringSet(key,null);
    }


    /**
     * 获取Preference
     *
     * @param context
     * @param key
     * @return
     */
    public static String getPreference(Context context, String key) {
        return context
                .getSharedPreferences(Config.APP_ID, Context.MODE_PRIVATE)
                .getString(key, null);
    }


    /**
     * 获取网络状态
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {

                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }

            }
        }
        return false;
    }


}
