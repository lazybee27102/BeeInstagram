package com.coderschool.beeiscoding.beeinstagram;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by beeiscoding on 13/03/2016.
 */
public class GlobalVariable {
    public static final String client_id = "e05c462ebd86446ea48a5af73769b602";
    public static final String KEY_INSTAGRAM_PHOTO = "instagram_photo";
    public static final String KEY_PHOTO_ID = "instagram_photo_id";
    public static final String KEY_VIDEO_URL = "instagram_video_url";
    public static final String KEY_VIDEO_OWNER = "instagram_video_url_owner";
    public static int getScreenWidth(Activity activity)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    public static int getScreenHeight(Activity activity)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    public static int getTabHeight(Activity activity)
    {
        return (int)(getScreenHeight(activity)*15/100);
    }

    public static int getImageHeight(Activity activity)
    {
        return (int)(getScreenHeight(activity)*50/100);
    }

    public static int getVideoPlayButtonHeight(Activity activity)
    {
        return (int)getImageHeight(activity)/4;
    }




}
