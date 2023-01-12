package com.example.kursovaya2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tests extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        init();
        getDataFromBD();
        setOnClickItem();
    }

    @Override
    public void onClick(View view){
        Intent newTest = new Intent(this, NewTest.class);
        startActivity(newTest);
    }

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private DatabaseReference mDataBase;
    private String TEST_KEY = "Test";
    private List<Test> listTest;

    private void init(){
        listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        listTest = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        mDataBase = FirebaseDatabase.getInstance().getReference(TEST_KEY);
    }
    private void getDataFromBD(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (listData.size() > 0) listData.clear();
                if (listTest.size() > 0) listTest.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Test test = ds.getValue(Test.class);
                    assert test != null;
                    listData.add(test.quest);
                    listTest.add(test);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDataBase.addValueEventListener(vListener);
    }

    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Test test = listTest.get(position);
                Intent showTest = new Intent(Tests.this, ShowTest.class);
                showTest.putExtra("test_quest", test.quest);
                showTest.putExtra("test_ans1", test.ans1);
                showTest.putExtra("test_ans2", test.ans2);
                showTest.putExtra("test_ans3", test.ans3);
                showTest.putExtra("test_ans4", test.ans4);
                showTest.putExtra("test_box1", test.box1.booleanValue());
                showTest.putExtra("test_box2", test.box2.booleanValue());
                showTest.putExtra("test_box3", test.box3.booleanValue());
                showTest.putExtra("test_box4", test.box4.booleanValue());
                startActivity(showTest);
            }
        });
    }
}
