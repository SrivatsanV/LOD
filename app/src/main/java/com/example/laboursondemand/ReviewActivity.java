package com.example.laboursondemand;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private TextInputLayout feedback;
    private RatingBar ratingBar;
    private Button submitButton;
    private TextView ratingTextView;
    private String TAG = ReviewActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_activity);


        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        submitButton = (Button) findViewById(R.id.submit_button);
        ratingTextView = (TextView) findViewById(R.id.rating_text_view);
        feedback = findViewById(R.id.feedback);
        firebaseFirestore = FirebaseFirestore.getInstance();


        submitButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (ratingBar.getRating() == 0.0)
                    ratingTextView.setText("Please Enter Rating ");
                else {
                    if (feedback.getEditText().getText().toString().compareTo("") == 0)
                        feedback.setError("Please enter text");
                    else if (feedback.getEditText().getText().toString().length() > 14)
                        feedback.setError("Length exceeding 14");
                    else {
                        feedback.setError(null);
                        ratingTextView.setText("Ratings : " + ratingBar.getRating());
                        submitReview();
                    }
                }

            }
        });
    }

    public void submitReview() {

        String feedbackString = feedback.getEditText().getText().toString();
        float ratingFloat = ratingBar.getRating();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Labour labour = new Labour(user.getUid(), feedbackString, ratingFloat);
        if (user != null) {
            firebaseFirestore.collection("Services").add(labour)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "SUCCESS");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, e.toString());
                        }
                    });
        }
    }
}
