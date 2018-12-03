package com.example.a15862.mytraveldiary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a15862.mytraveldiary.Entity.Comment;


import java.io.File;
import java.util.List;

public class MyCustomAdapterForComment extends RecyclerView.Adapter<MyCustomAdapterForComment.ViewHolder> {

    private List<Comment> upload;
    private Context context;
    private LayoutInflater mInflater;


    public MyCustomAdapterForComment(Context aContext, List<Comment> aupload) {
        context = aContext;  //saving the context we'll need it again.
        upload = aupload;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment currentCom = upload.get(position);
        holder.txtDisplayName.setText(currentCom.getUsername());
        holder.txtComment.setText(currentCom.getUserComment());
        // TODO:
        //holder.txtUserRates.setText(currentCom.getUser.getUserRates());
        //holder.txtLikesCount.setText(currentCom.getLikesCount());

    }


    @Override
    public int getItemCount() {
        return upload.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDisplayName;
        private TextView txtComment;
        private TextView txtUserRates;
        private TextView txtLikesCount;
        private CardView cardView;
        private ImageButton thumbUp;
        private int numLikes = 0;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            txtDisplayName = (TextView) itemView.findViewById(R.id.txtDisplayName);
            txtComment = (TextView) itemView.findViewById(R.id.txtComment);
            txtUserRates = (TextView) itemView.findViewById(R.id.txtUserRates);
            txtLikesCount = (TextView) itemView.findViewById(R.id.txtLikesCount);
            numLikes = Integer.parseInt(txtLikesCount.getText().toString());
            thumbUp = (ImageButton) itemView.findViewById(R.id.img_up);
            thumbUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (thumbUp.getImageAlpha() == 255) {
                        numLikes++;
                        txtLikesCount.setText(numLikes);
                        //thumbUp.setImageResource(R.drawable.thumbDown);
                        thumbUp.setImageAlpha(150);
                    } else if (thumbUp.getImageAlpha() == 150) {
                        numLikes--;
                        txtLikesCount.setText(numLikes);
                        //thumbUp.setImageResource(R.drawable.thumbDown);
                        thumbUp.setImageAlpha(255);
                    }

                }
            });

            //TODO: update database
        }
    }
}

