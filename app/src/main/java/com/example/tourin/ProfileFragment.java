package com.example.tourin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    private Button btnLogoutMain;
    TextView tvUsername,tvEmail;
    private String username, email;

    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tourin-839e2-default-rtdb.firebaseio.com/");
    public ProfileFragment(String username, String email) {
        // Required empty public constructor
        this.username = username;
        this.email = email;
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment(newInstance().username, newInstance().email);
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        tvUsername = view.findViewById(R.id.textUserName);
        tvEmail = view.findViewById(R.id.textUserEmail);
        btnLogoutMain = view.findViewById(R.id.btnLogoutMain);

        setData();

        btnLogoutMain.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getContext(), LoginActivity.class));
        });


        return view;
    }
    void setData(){
        tvEmail.setText(email);
        tvUsername.setText(username);
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = auth.getCurrentUser();
//        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    String username = dataSnapshot.child("name").getValue().toString();
//                    String email = dataSnapshot.child("email").getValue().toString();
//                    tvUsername.setText(username);
//                    tvEmail.setText(email);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

}