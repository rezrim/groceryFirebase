package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.crud.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private Button btnRegister;
    private TextView btnLoginLayout;
    private EditText username,password,passwordConfirm;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        btnRegister =  (Button) findViewById(R.id.btnRegister);
        btnLoginLayout = (TextView) findViewById(R.id.btnLoginLayout);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        passwordConfirm = (EditText) findViewById(R.id.passwordConfirm);

        db = FirebaseDatabase.getInstance().getReference();

        btnLoginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    if(password.getText().toString().equals(passwordConfirm.getText().toString())){
                        saveUser(new User(username.getText().toString(), password.getText().toString()));
                    }else{
                        Snackbar.make(findViewById(R.id.btnRegister), "Password Konfirmasi TIdak Sama", Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(findViewById(R.id.btnRegister), "Data Harus Diisi Semua", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    private void saveUser(User user){
        db.child("user").push().setValue(user).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                username.setText("");
                password.setText("");
                Snackbar.make(findViewById(R.id.btnRegister), "Data Berhasil Disimpan", Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
