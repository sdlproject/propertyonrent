package com.example.propertyonrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class registerPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseDatabase database;
    private DatabaseReference ref,newUser;
    private String uid;
    private Spinner spinner;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page_layout);
        Toast.makeText(getApplicationContext(), "Authentication failed2.",Toast.LENGTH_SHORT).show();

        spinner=(Spinner)findViewById(R.id.spinner2);
        mAuth = FirebaseAuth.getInstance();
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference().child("owners");

        List<String>categories=new ArrayList<>();
        categories.add("Choose Category");
        categories.add("Tenant");
        categories.add("Owner");
        ArrayAdapter<String>dataAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void gotoLoginPage(View view)
    {
        final String email=(((EditText)findViewById(R.id.txtemail)).getText()).toString();
        final String password=(((EditText)findViewById(R.id.txtpassword)).getText()).toString();
        final String conpassword=((EditText)findViewById(R.id.txtconpassword)).getText().toString();
        final String city=((EditText)findViewById(R.id.txtcity)).getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.VISIBLE);
                        if (task.isSuccessful())
                        {
                         //get user's id
                          uid=task.getResult().getUser().getUid();
                          //create user container;
                          //User user=new User(uid,email,city,password);
                          newUser=ref.child(uid);
                          newUser.child("uid").setValue(uid);
                          newUser.child("email").setValue(email);
                          newUser.child("city").setValue(city);

                            Toast.makeText(getApplicationContext(), "Registration Successful.",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            //startActivity(new Intent(getApplicationContext(),home_fragment.class));
                            //finish();
                        }
                        else
                        {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
