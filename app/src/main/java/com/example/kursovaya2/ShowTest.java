package com.example.kursovaya2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowTest extends AppCompatActivity implements View.OnClickListener {
    private TextView tQuest, tAns1, tAns2, tAns3, tAns4;
    public CheckBox tBox1, tBox2, tBox3, tBox4;
    private Boolean pBox1, pBox2, pBox3, pBox4;
    public String check;
    private Integer right = 0;

    private DatabaseReference mDataBase;
    private String TEST_KEY = "Test";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_test);
        init();
        getIntentTest();
    }

    private void init(){
        tQuest = findViewById(R.id.tQuest);
        tAns1 = findViewById(R.id.tAns1);
        tAns2 = findViewById(R.id.tAns2);
        tAns3 = findViewById(R.id.tAns3);
        tAns4 = findViewById(R.id.tAns4);
        tBox1 = findViewById(R.id.tBox1);
        tBox2 = findViewById(R.id.tBox2);
        tBox3 = findViewById(R.id.tBox3);
        tBox4 = findViewById(R.id.tBox4);
        mDataBase = FirebaseDatabase.getInstance().getReference(TEST_KEY);
    }

    private void getIntentTest(){
        Intent showTest = getIntent();
        if (showTest != null){
            tQuest.setText(showTest.getStringExtra("test_quest"));
            tAns1.setText(showTest.getStringExtra("test_ans1"));
            tAns2.setText(showTest.getStringExtra("test_ans2"));
            tAns3.setText(showTest.getStringExtra("test_ans3"));
            tAns4.setText(showTest.getStringExtra("test_ans4"));
            tBox1.setChecked(showTest.getBooleanExtra("test_box1", false));
            pBox1 = tBox1.isChecked();
            tBox1.setChecked(false);
            tBox2.setChecked(showTest.getBooleanExtra("test_box2", false));
            pBox2 = tBox2.isChecked();
            tBox2.setChecked(false);
            tBox3.setChecked(showTest.getBooleanExtra("test_box3", false));
            pBox3 = tBox3.isChecked();
            tBox3.setChecked(false);
            tBox4.setChecked(showTest.getBooleanExtra("test_box4", false));
            pBox4 = tBox4.isChecked();
            tBox4.setChecked(false);
        }
    }

    public void Check(){
        if (tBox1.isChecked() == pBox1){
            right += 1;
        }
        if(tBox2.isChecked() == pBox2){
            right += 1;
        }
        if(tBox3.isChecked() == pBox3){
            right += 1;
        }
        if(tBox4.isChecked() == pBox4){
            right += 1;
        }
        if (right == 4){
            check = "Всё верно!";
        }
        else{
            check = "Правильных ответов: " + right;
        }
    }

    @Override
    public void onClick(View view){
        Check();
        Intent proverka = new Intent(this, Proverka.class);
        proverka.putExtra("check",check);
        startActivity(proverka);
    }
}
