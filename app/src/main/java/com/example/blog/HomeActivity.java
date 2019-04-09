package com.example.blog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeTextView;
    private FirebaseAuth mAuth;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        downloadDataUser(user.getUid());



    }

    public void downloadDataUser(String idUser){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Class FirebaseDatabase merujuk ke database dari projek
        DatabaseReference myRef = database.getReference();
        myRef.child("Users").child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //daata snapshot ialah data yang akan kita download

                welcomeTextView.setText("Welcome "+ dataSnapshot.child("username").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public  void readBlog(View view){
        Intent intent = new Intent(HomeActivity.this,readBlog.class);
        startActivity(intent);
    }



    public void logout(View view){

        mAuth.signOut();
        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);

    }

    public void createBlog(View view){

        Intent intent = new Intent(HomeActivity.this,CreateBlogActivity.class);
        startActivity(intent);

    }
}
