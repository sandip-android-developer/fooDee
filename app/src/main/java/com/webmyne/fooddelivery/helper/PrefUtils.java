package com.webmyne.fooddelivery.helper;

import android.content.Context;

public class PrefUtils {
    public static String USER_ID = "UserId";
    public static String USER_PROFILE_KEY = "USER_PROFILE_KEY";
    public static String LOGGED_IN = "isLogin";
    private static String FCM_TOKEN = "fcm";
    private static String USER = "user";
    private static String PREFERRED_CITY = "city";

    public static void setFCMToken(Context ctx, String value) {
        Prefs.Companion.with(ctx).save(FCM_TOKEN, value);
    }

    public static String getFCMToken(Context ctx) {
        return Prefs.Companion.with(ctx).getString(FCM_TOKEN, " ");
    }

    public static void setLoggedIn(Context ctx, boolean value) {
        Prefs.Companion.with(ctx).save(LOGGED_IN, value);
    }

    public static void setUser(Context ctx, String value) {
        Prefs.Companion.with(ctx).save(USER, value);
    }

    public static String getUser(Context ctx) {
        return Prefs.Companion.with(ctx).getString(USER, "");
    }

    public static boolean isUserLoggedIn(Context ctx) {
        return Prefs.Companion.with(ctx).getBoolean(LOGGED_IN, false);
    }

    public static void setUserID(Context ctx, long value) {
        Prefs.Companion.with(ctx).save(USER_ID, value);
    }

    public static long getUserID(Context ctx) {
        return Prefs.Companion.with(ctx).getLong(USER_ID, 0);
    }

}
