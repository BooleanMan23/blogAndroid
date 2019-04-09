package com.example.blog;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class readBlog extends AppCompatActivity {
    static ArrayList<String> blog = new ArrayList<String>();
    static ArrayAdapter arrayAdapter;
    ListView myListView;
    EditText cariEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        blog.clear();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_blog);

        cariEditText = (EditText) findViewById(R.id.cariEditText);
        myListView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, blog);
        myListView.setAdapter(arrayAdapter);

        downloadBlog();



        cariEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                (readBlog.this).arrayAdapter.getFilter().filter(s);
                try {
                }

                catch (Exception E){
                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void downloadBlog(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Class FirebaseDatabase merujuk ke database dari projek
        DatabaseReference myRef = database.getReference();
        myRef.child("Blogs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                blog.clear();
                //daata snapshot ialah data yang akan kita download
                for(DataSnapshot blogSnapshot : dataSnapshot.getChildren()){
                        String judulBlog = blogSnapshot.child("judul").getValue().toString();
                        blog.add(judulBlog);
                    arrayAdapter.notifyDataSetChanged();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
