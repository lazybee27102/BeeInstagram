package com.coderschool.beeiscoding.beeinstagram.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderschool.beeiscoding.beeinstagram.Comment;
import com.coderschool.beeiscoding.beeinstagram.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by beeiscoding on 14/03/2016.
 */
public class AdapterComments extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Comment> arrayList;
    Context context;
    LayoutInflater inflater;

    public AdapterComments(ArrayList<Comment> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.comment_layout, parent, false);
        CommentViewHolder viewHolder = new CommentViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommentViewHolder imageViewHolder = (CommentViewHolder) holder;
        Comment comment = arrayList.get(position);
        imageViewHolder.userName.setText(comment.getUserName());
        imageViewHolder.userComment.setText(comment.getText());
        Picasso.with(context).load(comment.getUserURL()).into(imageViewHolder.imageView);
        imageViewHolder.time.setText(comment.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView userName, userComment, time;

        public CommentViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView_comment_user_url);
            userName = (TextView) itemView.findViewById(R.id.textView_comment_userName);
            userComment = (TextView) itemView.findViewById(R.id.textView_comment_text);
            time = (TextView) itemView.findViewById(R.id.textView_comment_time);
        }
    }
}
