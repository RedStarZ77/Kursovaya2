package com.example.kursovaya2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewTest extends AppCompatActivity {

    private EditText question, ans1, ans2, ans3, ans4;
    private CheckBox box1, box2, box3, box4;
    private String TEST_KEY = "Test";
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test);
        init();
    }

    private void init(){
        question = findViewById(R.id.question);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        box1 = findViewById(R.id.checkBox1);
        box2 = findViewById(R.id.checkBox2);
        box3 = findViewById(R.id.checkBox3);
        box4 = findViewById(R.id.checkBox4);
        mDataBase = FirebaseDatabase.getInstance().getReference(TEST_KEY);
    }

    public void onClickSaveTest(View view){
        String id = mDataBase.getKey();
        String quest = question.getText().toString();
        String answer1 = ans1.getText().toString();
        String answer2 = ans2.getText().toString();
        String answer3 = ans3.getText().toString();
        String answer4 = ans4.getText().toString();
        Boolean checkBox1 = box1.isChecked();
        Boolean checkBox2 = box2.isChecked();
        Boolean checkBox3 = box3.isChecked();
        Boolean checkBox4 = box4.isChecked();
        Test newTest = new Test(id, quest, answer1, answer2, answer3, answer4, checkBox1, checkBox2, checkBox3, checkBox4);
        if (!TextUtils.isEmpty(quest) && !TextUtils.isEmpty(answer1) && !TextUtils.isEmpty(answer2) && !TextUtils.isEmpty(answer3) && !TextUtils.isEmpty(answer4)) {
            mDataBase.push().setValue(newTest);
            Toast.makeText(this, "Тест сохранён", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
        }
        Intent tests = new Intent(this, Tests.class);
        startActivity(tests);
    }
}
