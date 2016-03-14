package com.coderschool.beeiscoding.beeinstagram;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderschool.beeiscoding.beeinstagram.RecyclerView.AdapterMainStream;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoShowFragment extends Fragment {
    private ImageView imageView_url;
    private RecyclerView recyclerView;
    private AdapterMainStream adapter_comments;
    private ArrayList<Comment> comments;
    private ImageView imageView_toolbar_profile;
    private TextView textView_toolbar_profile_userName;
    private TextView textView_toolbar_location;
    private TextView textView_toolbar_time;
    private ImageView location_icon;


    public PhotoShowFragment() {
        // Required empty public constructor
    }


    public static PhotoShowFragment CreateFragment(InstagramPhoto photo) {
        PhotoShowFragment frag = new PhotoShowFragment();
        Bundle arg = new Bundle();
        arg.putParcelable(GlobalVariable.KEY_INSTAGRAM_PHOTO, photo);
        frag.setArguments(arg);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final InstagramPhoto photo = getArguments().getParcelable(GlobalVariable.KEY_INSTAGRAM_PHOTO);

        //set Up Widget
        imageView_toolbar_profile = (ImageView) view.findViewById(R.id.imageView_toolbar_user_profile);
        textView_toolbar_profile_userName = (TextView) view.findViewById(R.id.textView_toolbar_userName);
        textView_toolbar_location = (TextView) view.findViewById(R.id.textView_toolbar_location);
        textView_toolbar_time = (TextView) view.findViewById(R.id.textView_toolbar_time);
        location_icon = (ImageView)view.findViewById(R.id.imageView_location_icon);

        if (photo.getProfileImageUrl() != null)
            Picasso.with(getContext()).load(photo.getProfileImageUrl()).transform(new CircleTransformation()).into(imageView_toolbar_profile);
        if(photo.getUserName()!=null)
            textView_toolbar_profile_userName.setText(photo.getUserName());
        if(!photo.getLocation().equals("null"))
        {
            textView_toolbar_location.setText(photo.getLocation());
            location_icon.setVisibility(View.VISIBLE);
        }

        if(photo.getTime()!=null)
            textView_toolbar_time.setText(photo.getTime());

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_comments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        comments = new ArrayList<>();



        //getComments
        String id = photo.getId();
        AsyncHttpClient_RequestComments asyncHttpClient_requestComments = new AsyncHttpClient_RequestComments(getContext(), id, new AsyncHttpClient_RequestComments.getResponse() {
            @Override
            public void AccessResponse(ArrayList<Comment> comments) {
                adapter_comments = new AdapterMainStream(comments, getActivity(), photo);
                recyclerView.setAdapter(adapter_comments);
            }
        },false);
        asyncHttpClient_requestComments.callRequest();






    }


}
