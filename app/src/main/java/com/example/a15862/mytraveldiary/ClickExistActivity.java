package com.example.a15862.mytraveldiary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.a15862.mytraveldiary.DAO.CommentDAO;
import com.example.a15862.mytraveldiary.DAO.PlaceDAO;
import com.example.a15862.mytraveldiary.Entity.Comment;
import com.example.a15862.mytraveldiary.Entity.Place;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ClickExistActivity extends Activity {
    private Place currentPlace;
    private TextView txtPlaceName;
    private RatingBar ratingBar;
    PlaceDAO pd;

    private TextView txtTotalComments;
    private TextView txtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pd = new PlaceDAO();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_exist);
        txtPlaceName = findViewById(R.id.txtPlaceName);
        txtCategory = findViewById(R.id.txtCategory);
        ratingBar = findViewById(R.id.ratingBar);
        txtTotalComments = findViewById(R.id.txtTotalComments);

        //TODO: Move to ViewCommentsActivity
//        editText = findViewById(R.id.editText);
//        btnSave = findViewById(R.id.btnSaveReturn);
//        btnJump = findViewById(R.id.btnSaveJump);
//        commentList = findViewById(R.id.commentList);
//        commentList.setHasFixedSize(true);
//        commentList.setLayoutManager(new LinearLayoutManager(this));
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                score = rating;
//            }
//        });

//        //TODO: move to ViewCommentsActivity
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("Comment").whereEqualTo("placeName", currentPlace.getPlaceName()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                for (DocumentSnapshot d : queryDocumentSnapshots) {
//                    Comment c = d.toObject(Comment.class);
//                    commentArray.add(c);
//                }
//            }
//        });


        Bundle info = getIntent().getExtras();
        currentPlace = (Place) info.getSerializable("Place");
        txtPlaceName.setText(currentPlace.getPlaceName());
        List<String> categorys = currentPlace.getCategory();
        String cats = "";
        for (int i = 0; i < categorys.size(); i++) {
            cats = new StringBuilder(cats)
                    .append(categorys.get(i)).append(" ").toString();
        }
        ;
        txtCategory.setText(cats);
        float rating = currentPlace.getTotalScore() / currentPlace.getScoreCount();
        ratingBar.setRating(rating);
        //TODO:
        //int totalComments = currentPlace.getTotalComments;
        txtTotalComments.setText(currentPlace.getTotalComment());
        txtTotalComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClickExistActivity.this, ViewCommentsActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("Place", currentPlace);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

//        //TODO: move to ViewCommentsActivity
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("Comment").whereEqualTo("placeName", currentPlace.getPlaceName()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                for (DocumentSnapshot d : queryDocumentSnapshots) {
//                    Comment c = d.toObject(Comment.class);
//                    commentArray.add(c);
//                }
//                mAdapter = new MyCustomAdapterForComment(ClickExistActivity.this, commentArray);
//                commentList.setAdapter(mAdapter);
//
//            }
//        });
//
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                storeComment();
//                currentPlace.addScore(score);
//                pd.updateData(currentPlace);
//                Intent back = new Intent(ClickExistActivity.this, MapActivity.class);
//                startActivity(back);
//            }
//        });
//
//        btnJump.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                storeComment();
//                currentPlace.addScore(score);
//                pd.updateData(currentPlace);
//                Intent intent = new Intent(ClickExistActivity.this, AddDiaryActivity.class);
//                Bundle b = new Bundle();
//                b.putSerializable("Place", currentPlace);
//                intent.putExtras(b);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void storeComment() {
//        String comment = editText.getText().toString();
//        SharedPreferences load = getSharedPreferences("user", Context.MODE_PRIVATE);
//        Comment c = new Comment(load.getString("displayName", "123"), currentPlace.getPlaceName(), comment);
//        CommentDAO cd = new CommentDAO();
//        cd.addComment(c,0);
//    }
    }

}
