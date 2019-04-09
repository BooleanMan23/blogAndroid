package com.example.blog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText usernameEditText;
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private String username;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);

        //JIKA SUDAH ADA USER LANGSUNG MEMBUKA INTENT KE HOMEPAGE
        user = mAuth.getCurrentUser();
        if(user != null){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }

    }


    public void register(View view){

        try {
            email = emailEditText.getText().toString();
            password = passwordEditText.getText().toString();
            username = usernameEditText.getText().toString();
            Log.i("informasi register", email);
            Log.i("informasi register", password);
            Log.i("informasi register", username);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Authentication success.",
                                        Toast.LENGTH_SHORT).show();
                                Log.i("user ID", mAuth.getUid().toString());
                                uploadToDatabase(email, username, mAuth.getUid());

                                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                                startActivity(intent);



                            } else {

                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        }
        catch (Exception E){


        }







    }

    public void uploadToDatabase(String email, String username, String id){

        Log.i("Uploading to database", "Uploading to database");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        ModelUsers newUser = new ModelUsers(email, username);
        myRef.child("Users").child(id).setValue(newUser);


    }

    public void toLoginPage(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}


