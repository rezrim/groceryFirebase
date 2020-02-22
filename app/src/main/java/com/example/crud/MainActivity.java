package com.example.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.crud.model.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView btnRegisterLayout;
    private EditText username,password;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin =  (Button) findViewById(R.id.btnLogin);
        btnRegisterLayout = (TextView) findViewById(R.id.btnRegisterLayout);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        db = FirebaseDatabase.getInstance().getReference();

        btnRegisterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    db.child("user").orderByChild("username").equalTo(username.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    System.out.println(ds.child("password").getValue());

                                    if(ds.child("password").getValue().toString().equals(password.getText().toString())) {
                                        Intent intent = new Intent(MainActivity.this, Home.class);
                                        startActivity(intent);
                                    }else{
                                        Snackbar.make(findViewById(R.id.btnLogin), "Password Salah", Snackbar.LENGTH_LONG).show();
                                    }
                                }
//
                            }else{
                                Snackbar.make(findViewById(R.id.btnLogin), "Username Tidak ditemukan", Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Snackbar.make(findViewById(R.id.btnLogin), "Data Tidak ditemukan", Snackbar.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Snackbar.make(findViewById(R.id.btnLogin), "Data Harus Diisi Semua", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

}
