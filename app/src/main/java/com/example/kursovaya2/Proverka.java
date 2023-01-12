package com.example.kursovaya2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Proverka extends AppCompatActivity implements View.OnClickListener {

    private TextView rAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proverka);
        init();
        Bundle arguments = getIntent().getExtras();
        String ans = arguments.get("check").toString();
        rAns.setText(ans);
    }

    private void init(){
        rAns = (TextView) findViewById(R.id.rAns);
    }

    @Override
    public void onClick(View view) {
        Intent tests = new Intent(this, Tests.class);
        startActivity(tests);
    }
}
