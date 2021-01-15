package com.webmyne.modak.helper;

import android.content.Context;

import com.webmyne.modak.ui.BaseActivity;

public class PrefUtils {
    public static String USER_ID = "UserId";
    public static String USER_Email = "UserEmail";
    public static String USER_PROFILE_KEY = "USER_PROFILE_KEY";
    public static String LOGGED_IN = "isLogin";
    private static String FCM_TOKEN = "fcm";
    private static String USER = "user";
    private static String PREFERRED_CITY = "city";
    private static String CardData = "cartdata";
    private static String REVIEWSCOUNT = "reviowscount";
    private static String CITY_Name = "cityname";
    private static String ADD_ITEM_COUNT = "additemcount";
    private static String COUPAN_APPLY = "coupan_apply";

    public static void setFCMToken(Context ctx, String value) {
        Prefs.Companion.with(ctx).save(FCM_TOKEN, value);
    }

    public static String getFCMToken(Context ctx) {
        return Prefs.Companion.with(ctx).getString(FCM_TOKEN, " ");
    }

    public static void setUserLoggedIn(Context ctx, boolean value) {
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

    public static void setUserEmail(Context ctx, String value) {
        Prefs.Companion.with(ctx).save(USER_Email, value);
    }

    public static String getUserEmail(Context ctx) {
        return Prefs.Companion.with(ctx).getString(USER_Email, "");
    }

    public static void setCoupanApply(Context ctx, String value) {
        Prefs.Companion.with(ctx).save(COUPAN_APPLY, value);
    }

    public static String getCoupanApply(Context ctx) {
        return Prefs.Companion.with(ctx).getString(COUPAN_APPLY, "");
    }

    public static void setCartData(Context ctx, String value) {
        Prefs.Companion.with(ctx).save(CardData, value);
    }

    public static String getCardData(Context ctx) {
        return Prefs.Companion.with(ctx).getString(CardData, "");
    }

    public static void setReviewsCount(Context ctx, int value) {
        Prefs.Companion.with(ctx).save(REVIEWSCOUNT, value);
    }

    public static int getReviewsCount(Context ctx) {
        return Prefs.Companion.with(ctx).getInt(REVIEWSCOUNT, 0);
    }
    public static void setAddItemCount(Context ctx, int value) {
        Prefs.Companion.with(ctx).save(ADD_ITEM_COUNT, value);
    }

    public static int getAddItemCount(Context ctx) {
        return Prefs.Companion.with(ctx).getInt(ADD_ITEM_COUNT, 0);
    }
    public static void setCityname(Context ctx, String value) {
        Prefs.Companion.with(ctx).save(CITY_Name, value);
    }

    public static String getCityname(Context ctx) {
        return Prefs.Companion.with(ctx).getString(CITY_Name, "");
    }


}
