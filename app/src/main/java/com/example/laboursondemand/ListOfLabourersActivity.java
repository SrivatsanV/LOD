package com.example.laboursondemand;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ListOfLabourersActivity extends AppCompatActivity {
    private static final String TAG = ListOfLabourersActivity.class.getName();

    private Context context;
    private RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_labourers);
        context = this;
        recyclerView = findViewById(R.id.list_of_labourers_rv);
        progressBar = findViewById(R.id.list_of_labourers_pb);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();
    }

    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Labourer").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Labourer> labourerList = new ArrayList<>();
                        for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                            Labourer labourer = document.toObject(Labourer.class);
                            labourerList.add(labourer);
                            Log.d(TAG, "Firebase : " + labourer.toString());
                        }
                        display(labourerList);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        errorHandling();
                    }
                });
    }

    private void display(List<Labourer> labourerList) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(new LabourersAdapter(context, labourerList));
    }

    private void errorHandling() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(context, "Some error occurred while fetching data",
                Toast.LENGTH_SHORT).show();
    }
}
