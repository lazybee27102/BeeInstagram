package com.coderschool.beeiscoding.beeinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coderschool.beeiscoding.beeinstagram.RecyclerView.AdapterComments;

import java.util.ArrayList;

public class ShowAllCommentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterComments adapterComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_comment);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_comments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String id = getIntent().getStringExtra(GlobalVariable.KEY_PHOTO_ID);
        AsyncHttpClient_RequestComments asyncHttpClient_requestComments = new AsyncHttpClient_RequestComments(this, id, new AsyncHttpClient_RequestComments.getResponse() {
            @Override
            public void AccessResponse(ArrayList<Comment> comments) {
                adapterComments = new AdapterComments(comments,ShowAllCommentActivity.this);
                recyclerView.setAdapter(adapterComments);
            }
        },true);
        asyncHttpClient_requestComments.callRequest();


    }
}
