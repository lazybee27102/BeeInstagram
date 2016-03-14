package com.coderschool.beeiscoding.beeinstagram;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by beeiscoding on 13/03/2016.
 */
public class InstagramPhoto implements Parcelable {
    private String id;
    private int commentCount;

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String userName;
    private String caption;
    private String imageUrl;
    private int likesCount;
    private String imagethumnail;
    private String location;
    private String time;
    private String profileImageUrl;
    private String type;
    private String videoLink;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public InstagramPhoto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCaption() {
        if(!caption.equals("null"))
        {
            try {
                JSONObject jsonObject = new JSONObject(caption);
                String result = jsonObject.getString("text");
                return result;
            } catch (JSONException e) {
                e.printStackTrace();
                return "null";
            }
        }
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getImagethumnail() {
        return imagethumnail;
    }

    public void setImagethumnail(String imagethumnail) {
        this.imagethumnail = imagethumnail;
    }

    public String getLocation() {
        if(!location.equals("null"))
        {
            try {
                JSONObject jsonObject = new JSONObject(location);
                String name = jsonObject.getString("name");
                return "at" + name;

            } catch (JSONException e) {
                e.printStackTrace();
                return "null";
            }

        }
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        String result = (String) DateUtils.getRelativeTimeSpanString(Long.valueOf(time),Long.valueOf(System.currentTimeMillis()/1000), 0);
        return result;

    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.commentCount);
        dest.writeString(this.userName);
        dest.writeString(this.caption);
        dest.writeString(this.imageUrl);
        dest.writeInt(this.likesCount);
        dest.writeString(this.imagethumnail);
        dest.writeString(this.location);
        dest.writeString(this.time);
        dest.writeString(this.profileImageUrl);
    }

    protected InstagramPhoto(Parcel in) {
        this.id = in.readString();
        this.commentCount = in.readInt();
        this.userName = in.readString();
        this.caption = in.readString();
        this.imageUrl = in.readString();
        this.likesCount = in.readInt();
        this.imagethumnail = in.readString();
        this.location = in.readString();
        this.time = in.readString();
        this.profileImageUrl = in.readString();
    }

    public static final Creator<InstagramPhoto> CREATOR = new Creator<InstagramPhoto>() {
        public InstagramPhoto createFromParcel(Parcel source) {
            return new InstagramPhoto(source);
        }

        public InstagramPhoto[] newArray(int size) {
            return new InstagramPhoto[size];
        }
    };
}
