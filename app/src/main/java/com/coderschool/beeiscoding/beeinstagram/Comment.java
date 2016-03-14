package com.coderschool.beeiscoding.beeinstagram;

import android.text.format.DateUtils;

/**
 * Created by beeiscoding on 14/03/2016.
 */
public class Comment {
    String userName;
    String text;
    String userURL;
    String time;

    public Comment() {
    }

    public Comment(String userName, String text, String userURL, String time) {
        this.userName = userName;
        this.text = text;
        this.userURL = userURL;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserURL() {
        return userURL;
    }

    public void setUserURL(String userURL) {
        this.userURL = userURL;
    }

    public String getTime() {
        String result = (String) DateUtils.getRelativeTimeSpanString(Long.valueOf(time),Long.valueOf(System.currentTimeMillis()/1000), 0);
        return result;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
