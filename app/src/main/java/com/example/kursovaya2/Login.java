package com.example.kursovaya2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    //    private DatabaseReference mDataBase;
//    public String poleLogin, dataLogin;
    public EditText loginMail, loginPassword;
    //    public Boolean log = false;
    private Button btnLogin;
    private String USER_KEY = "User";
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        loginMail = findViewById(R.id.loginMail);
        loginPassword = findViewById(R.id.loginPassword);
//        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        mAuth = FirebaseAuth.getInstance();
        btnLogin = findViewById(R.id.ButtonLogScr);
        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
    }

    private void loginUser() {
        String email = loginMail.getText().toString();
        String password = loginPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            loginMail.setError("Email cannot be empty");
            loginMail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            loginPassword.setError("Password cannot be empty");
            loginPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, Tests.class));
                    } else {
                        Toast.makeText(Login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
//    private void getDataFromBD(){
//        ValueEventListener vListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    User user = ds.getValue(User.class);
//                    assert user != null;
//                    poleLogin = loginMail.getText().toString();
//                    dataLogin = user.mail;
//                    if (poleLogin == dataLogin){
//                        log = true;
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        mDataBase.addValueEventListener(vListener);
//    }

//    @Override
//    public void onClick(View view) {
//                getDataFromBD();
//                if (log == true){
//                    Intent tests = new Intent(this, Tests.class);
//                    startActivity(tests);
//                }
//                else {
//                    Toast.makeText(this, "Введены неправильные данные", Toast.LENGTH_SHORT).show();
//                }
//    }
