package com.coderschool.beeiscoding.beeinstagram;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beeiscoding on 13/03/2016.
 */
class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager,Context context) {
        super(manager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    public ImageView getTabView(int postion,String photoThumnail)
    {
        int lenght = GlobalVariable.getTabHeight((Activity) context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(lenght, lenght);
        ImageView tab1 = (ImageView) LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        tab1.setLayoutParams(params);
        Picasso.with(context).load(photoThumnail).fit().into(tab1);
        return tab1;

    }
}
