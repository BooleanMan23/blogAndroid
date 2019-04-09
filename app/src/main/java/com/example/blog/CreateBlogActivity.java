package com.example.blog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CreateBlogActivity extends AppCompatActivity {

    EditText judulEditText;
    EditText isiEditText;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private  String judul;
    private  String isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_blog);
        judulEditText = (EditText) findViewById(R.id.judulEditText);
        isiEditText = (EditText) findViewById(R.id.isiEditText);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


    }


    public  void createBlog(View view){

        try {
            judul = judulEditText.getText().toString();
            isi = isiEditText.getText().toString();
            Log.i("informasi blog", judul);
            Log.i("informasi blog", isi);
         uploadBlogToDatabase(judul, isi);
            Toast.makeText(CreateBlogActivity.this, "Blog Uploaded.",
                    Toast.LENGTH_SHORT).show();


        }
        catch (Exception E){



        }





    }

    public void uploadBlogToDatabase(String judul, String isi){

        Log.i("Uploading to database", "Uploading BLOG to database");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        String idUser = mAuth.getUid().toString();
        String blogId = myRef.push().getKey();
        Log.i("informasi blog", idUser);
        Log.i("informasi blog", blogId);
        ModelBlog newBlog = new ModelBlog(blogId, idUser, judul, isi);
        myRef.child("Blogs").child(blogId).setValue(newBlog);

    }

}
