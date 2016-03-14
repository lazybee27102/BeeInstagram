package com.coderschool.beeiscoding.beeinstagram;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by beeiscoding on 13/03/2016.
 */
public class AsyncHttpClient_RequestComments {
    ArrayList<Comment> arrayList = new ArrayList<>();
    private Context context;
    private AsyncHttpClient client = new AsyncHttpClient();
    private getResponse delegate;
    private String id;
    private boolean getAllComments;

    public AsyncHttpClient_RequestComments(Context context, String id, getResponse delegate, boolean getAll) {
        this.context = context;
        this.delegate = delegate;
        this.id = id;
        this.getAllComments = getAll;
    }

    public void callRequest() {
        String API_url = "https://api.instagram.com/v1/media/" + id + "/comments?client_id=" + GlobalVariable.client_id;

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
                        Comment comment = new Comment();
                        comment.setUserName(jsonObject.getJSONObject("from").getString("username"));
                        comment.setUserURL(jsonObject.getJSONObject("from").getString("profile_picture"));
                        Log.d("URL", comment.getUserURL());
                        comment.setText(jsonObject.getString("text"));
                        comment.setTime(jsonObject.getString("created_time"));

                        if (arrayList.size() <= 15)
                            arrayList.add(comment);
                        else
                        {
                            if (!getAllComments) {
                                break;
                            } else {
                                if (arrayList.size() <= 100)
                                    arrayList.add(comment);
                            }
                        }



                    }
                    delegate.AccessResponse(arrayList);


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

    public interface getResponse {
        void AccessResponse(ArrayList<Comment> photos);

    }
}
