package com.coderschool.beeiscoding.beeinstagram.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coderschool.beeiscoding.beeinstagram.Comment;
import com.coderschool.beeiscoding.beeinstagram.GlobalVariable;
import com.coderschool.beeiscoding.beeinstagram.InstagramPhoto;
import com.coderschool.beeiscoding.beeinstagram.PlayVideo;
import com.coderschool.beeiscoding.beeinstagram.R;
import com.coderschool.beeiscoding.beeinstagram.ShowAllCommentActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by beeiscoding on 14/03/2016.
 */
public class AdapterMainStream extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_IMAGE = 1;
    private static final int TYPE_COMMENT = 2;
    InstagramPhoto photo;
    ArrayList<Comment> arrayList;
    Context context;
    LayoutInflater inflater;

    public AdapterMainStream(ArrayList<Comment> arrayList, Context context, InstagramPhoto photo) {
        this.arrayList = arrayList;
        this.context = context;
        this.photo = photo;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_IMAGE : TYPE_COMMENT;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View v = inflater.inflate(R.layout.image_layout, parent, false);
            ImageViewHolder viewHolder = new ImageViewHolder(context, v);
            return viewHolder;
        } else {
            View v = inflater.inflate(R.layout.comment_layout, parent, false);
            CommentViewHolder viewHolder = new CommentViewHolder(v);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_COMMENT: {
                CommentViewHolder imageViewHolder = (CommentViewHolder) holder;
                Comment comment = arrayList.get(position - 1);
                imageViewHolder.userName.setText(comment.getUserName());
                imageViewHolder.userComment.setText(comment.getText());
                Picasso.with(context).load(comment.getUserURL()).into(imageViewHolder.imageView);
                imageViewHolder.time.setText(comment.getTime());

            }
            break;
            case TYPE_IMAGE: {
                final ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, GlobalVariable.getImageHeight((Activity) context));
                imageViewHolder.imageView_photo.setLayoutParams(params);

                //Type
                String type  = photo.getType();
                if(type.equals("video"))
                {
                    imageViewHolder.relativeLayout.setBackgroundColor(Color.parseColor("#bf000000"));
                    imageViewHolder.imageView_video.setVisibility(View.VISIBLE);
                    int lenght = GlobalVariable.getVideoPlayButtonHeight((Activity) context);
                    FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(lenght,lenght);
                    params1.gravity = Gravity.CENTER;
                    imageViewHolder.imageView_video.setLayoutParams(params1);

                    imageViewHolder.imageView_video.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(context, PlayVideo.class);
                            i.putExtra(GlobalVariable.KEY_VIDEO_URL,photo.getVideoLink());
                            i.putExtra(GlobalVariable.KEY_VIDEO_OWNER,photo.getUserName());
                            context.startActivity(i);

                        }
                    });
                }


                //Likes count
                if(photo.getLikesCount()<2)
                    imageViewHolder.likes_count.setText(String.valueOf(photo.getLikesCount()) + " like");
                else
                    imageViewHolder.likes_count.setText(String.valueOf(photo.getLikesCount()) + " likes");
                //Comment Count
                if(photo.getLikesCount()<2)
                    imageViewHolder.comment_count.setText(String.valueOf(photo.getCommentCount()) + " comment");
                else
                    imageViewHolder.comment_count.setText(String.valueOf(photo.getLikesCount()) + " comments");

                //Handle OnCLick comments
                imageViewHolder.comment_count.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, ShowAllCommentActivity.class);
                        i.putExtra(GlobalVariable.KEY_PHOTO_ID,photo.getId());
                        context.startActivity(i);
                    }
                });

                //Status
                if (!photo.getCaption().equals("null")) {
                    imageViewHolder.textView_caption.setVisibility(View.VISIBLE);
                    String text = "<font color='#2962ff'>" + photo.getUserName() + " " + "</font>" + photo.getCaption();
                    imageViewHolder.textView_caption.setText(Html.fromHtml(text));
                } else {
                    imageViewHolder.textView_caption.setVisibility(View.GONE);
                }

                //Main Photo
                Picasso.with(context).load(photo.getImageUrl()).fit().centerCrop().into(imageViewHolder.imageView_photo);


                //Like Animation
                imageViewHolder.button_love.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (imageViewHolder.isLiked()) {
                            Drawable drawable = context.getResources().getDrawable(R.drawable.ic_heart_outline_grey600_36dp);
                            imageViewHolder.button_love.setImageDrawable(drawable);
                            imageViewHolder.setLiked(false);
                        } else {
                            Drawable drawable = context.getResources().getDrawable(R.drawable.ic_heart_white_36dp);
                            drawable.setColorFilter(0xffe53935, PorterDuff.Mode.MULTIPLY);
                            imageViewHolder.button_love.setImageDrawable(drawable);
                            imageViewHolder.setLiked(true);
                        }
                    }


                });

                imageViewHolder.imageView_photo.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (imageViewHolder.isLiked()) {
                            Drawable drawable = context.getResources().getDrawable(R.drawable.ic_heart_outline_grey600_36dp);
                            imageViewHolder.button_love.setImageDrawable(drawable);
                            imageViewHolder.setLiked(false);
                        } else {
                            Drawable drawable = context.getResources().getDrawable(R.drawable.ic_heart_white_36dp);
                            drawable.setColorFilter(0xffe53935, PorterDuff.Mode.MULTIPLY);
                            imageViewHolder.button_love.setImageDrawable(drawable);
                            imageViewHolder.setLiked(true);
                        }
                        return true;
                    }
                });

                imageViewHolder.button_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Do something", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            break;
        }

    }


    @Override
    public int getItemCount() {
        return arrayList.size() + 1;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_photo,imageView_video;
        TextView textView_caption,likes_count,comment_count;
        ImageView button_love, button_share;
        boolean liked = false;
        RelativeLayout relativeLayout;


        public ImageViewHolder(Context context, View itemView) {
            super(itemView);
            imageView_photo = (ImageView) itemView.findViewById(R.id.imageView_photo_upload);
            textView_caption = (TextView) itemView.findViewById(R.id.textView_caption);
            button_love = (ImageView) itemView.findViewById(R.id.imageButton_love);
            button_share = (ImageView) itemView.findViewById(R.id.imageButton_share);
            likes_count = (TextView)itemView.findViewById(R.id.textView_likes_count);
            comment_count = (TextView)itemView.findViewById(R.id.textView_comments_count);
            imageView_video = (ImageView)itemView.findViewById(R.id.imageView_play_video);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout_black_wrapper);

        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }


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
