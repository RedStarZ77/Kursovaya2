package com.example.kursovaya2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonLog;
    Button buttonReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLog = (Button) findViewById(R.id.ButtonLog);
        buttonLog.setOnClickListener(this);
        buttonReg = (Button) findViewById(R.id.ButtonReg);
        buttonReg.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.ButtonLog:
                Intent login = new Intent(this, Login.class);
                startActivity(login);
                break;
            case R.id.ButtonReg:
                Intent registration = new Intent(this, Registration.class);
                startActivity(registration);
                break;
        }
    }
}


//    private ListView listView;
//    private ArrayAdapter<String> adapter;
//    private List<String> listData;
//    private DatabaseReference mDataBase;
//    private String TEST_KEY = "Test";
//
//    private void init(){
//        listView = findViewById(R.id.listView);
//        listData = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
//        listView.setAdapter(adapter);
//        mDataBase = FirebaseDatabase.getInstance().getReference(TEST_KEY);
//    }
