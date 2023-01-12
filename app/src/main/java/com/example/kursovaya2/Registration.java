package com.example.kursovaya2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    private EditText rName, rSurname, rMail, rPassword;
    private Switch administrator;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";
    FirebaseAuth mAuth;
    private Button btnRegister;

    final String TAG = "lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Log.d(TAG, "Registration onStart()");
        init();
    }

    private void init() {
        rName = findViewById(R.id.rName);
        rSurname = findViewById(R.id.rSurname);
        rMail = findViewById(R.id.rMail);
        rPassword = findViewById(R.id.rPassword);
        administrator = findViewById(R.id.administator);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        mAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.buttonReg);
        btnRegister.setOnClickListener(view -> {
            createUser();
        });
    }

    private void createUser() {
        String email = rMail.getText().toString();
        String password = rPassword.getText().toString();
//        String name = rName.getText().toString();
//        String surname = rSurname.getText().toString();
//        Boolean admin = administrator.isChecked();

        if (TextUtils.isEmpty(email)) {
            rMail.setError("Email cannot be empty");
            rMail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            rPassword.setError("Password cannot be empty");
            rPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Registration.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Registration.this, MainActivity.class));
                    } else {
                        Toast.makeText(Registration.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
//    public void onClickSave(View view){
//        String id = mDataBase.getKey();
//        String name = rName.getText().toString();
//        String surname = rSurname.getText().toString();
//        String mail = rMail.getText().toString();
//        String password = rPassword.getText().toString();
//        Boolean admin = administrator.isChecked();
//        User newUser = new User(id, name, surname, mail, password, admin);
//        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(mail) && !TextUtils.isEmpty(password)) {
//            mDataBase.push().setValue(newUser);
//            Toast.makeText(this, "Пользователь сохранён", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
//        }
//        Intent main = new Intent(this, MainActivity.class);
//        startActivity(main);
//    }

