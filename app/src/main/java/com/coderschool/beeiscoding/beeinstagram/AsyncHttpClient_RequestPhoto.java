package com.coderschool.beeiscoding.beeinstagram;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;

/**
 * Created by beeiscoding on 13/03/2016.
 */
public class AsyncHttpClient_RequestPhoto {
    ArrayList<InstagramPhoto> arrayList = new ArrayList<>();
    private Context context;
    private AsyncHttpClient client = new AsyncHttpClient();
    private getResponse delegate;
    private ProgressDialog progressDialog;
    public interface getResponse
    {
        void AccessResponse(ArrayList<InstagramPhoto> photos);

    }

    public AsyncHttpClient_RequestPhoto(Context context, getResponse delegate) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading data");
        progressDialog.show();
        this.delegate = delegate;
    }

    public void callRequest()
    {
        String API_url = "https://api.instagram.com/v1/media/popular?client_id=" + GlobalVariable.client_id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(API_url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                JSONArray jsonArray = null;
                try {

                    jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        //create photoInstagram
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        InstagramPhoto photo = new InstagramPhoto();
                        String type = jsonObject.getString("type");
                        photo.setType(type);
                        if(type.equals("video"))
                        {
                            photo.setVideoLink(jsonObject.getJSONObject("videos").getJSONObject("standard_resolution").getString("url"));
                        }
                        photo.setImageUrl(jsonObject.getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
                        photo.setImagethumnail(jsonObject.getJSONObject("images").getJSONObject("thumbnail").getString("url"));
                        photo.setCaption(jsonObject.getString("caption"));
                        photo.setProfileImageUrl(jsonObject.getJSONObject("user").getString("profile_picture"));
                        photo.setLikesCount(jsonObject.getJSONObject("likes").getInt("count"));
                        photo.setTime(jsonObject.getString("created_time"));
                        photo.setId(jsonObject.getString("id"));
                        photo.setLocation(jsonObject.getString("location"));
                        photo.setUserName(jsonObject.getJSONObject("user").getString("username"));
                        photo.setCommentCount(jsonObject.getJSONObject("comments").getInt("count"));
                        arrayList.add(photo);
                    }
                    delegate.AccessResponse(arrayList);
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

        });
    }
}
