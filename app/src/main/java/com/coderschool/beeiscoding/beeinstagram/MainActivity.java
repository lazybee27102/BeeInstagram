package com.coderschool.beeiscoding.beeinstagram;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private ArrayList<InstagramPhoto> photos;
    private LinearLayout linearLayout_tab_wrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        photos = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),this);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        linearLayout_tab_wrapper = (LinearLayout)findViewById(R.id.tab_wrapper);
        int lenght = GlobalVariable.getTabHeight(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,lenght);
        tabLayout.setLayoutParams(params);



        AsyncHttpClient_RequestPhoto request = new AsyncHttpClient_RequestPhoto(this,new AsyncHttpClient_RequestPhoto.getResponse() {
            @Override
            public void AccessResponse(ArrayList<InstagramPhoto> photos) {
                for(int i = 0;i<photos.size();i++)
                {
                    InstagramPhoto photo = photos.get(i);
                    PhotoShowFragment photoFragment = PhotoShowFragment.CreateFragment(photo);
                    adapter.addFrag(photoFragment);
                }
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);

                for (int i = 0; i < photos.size(); i++) {

                    String thumnail = photos.get(i).getImagethumnail();
                    tabLayout.getTabAt(i).setCustomView(adapter.getTabView(i,thumnail));
                }


            }
        });
        request.callRequest();


    }


}
