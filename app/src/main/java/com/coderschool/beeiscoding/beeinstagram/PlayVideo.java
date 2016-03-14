package com.coderschool.beeiscoding.beeinstagram;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class PlayVideo extends AppCompatActivity {
    private VideoView videoView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        videoView = (VideoView) findViewById(R.id.videoView_video);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        String url = getIntent().getStringExtra(GlobalVariable.KEY_VIDEO_URL);
        String userName = getIntent().getStringExtra(GlobalVariable.KEY_VIDEO_OWNER);
        toolbar.setTitle(Html.fromHtml("<font color ='#ffffff'>Playing " + userName +"'s video"  + "</font>"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);



        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(640, 640);
        params.gravity = Gravity.CENTER;
        videoView.setLayoutParams(params);



        Log.d("VIDEO", url);
        videoView.setVideoPath(url);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });


    }


}
