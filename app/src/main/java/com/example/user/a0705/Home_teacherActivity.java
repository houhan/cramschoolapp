package com.example.user.a0705;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_teacherActivity extends AppCompatActivity {
    private String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_teacher);

        Intent intent = this.getIntent();
        UID = intent.getStringExtra("UID");

        Button button = (Button) findViewById(R.id.billboard);//取得按鈕
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Home_teacherActivity.this, BillboardActivity.class);
                Home_teacherActivity.this.startActivity(intent);
            }
        });//將這個Listener綑綁在這個Button

        Button button2 = (Button) findViewById(R.id.qk);//取得按鈕
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Home_teacherActivity.this, QkActivity.class);
                Home_teacherActivity.this.startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.Catch);//取得按鈕
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Home_teacherActivity.this, ShuttleActivity.class);
                Home_teacherActivity.this.startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.schedule);//取得按鈕
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Home_teacherActivity.this, ScheduleActivity.class);
                Home_teacherActivity.this.startActivity(intent);
            }
        });


        Button button5 = (Button) findViewById(R.id.create);//取得按鈕
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Home_teacherActivity.this, CreateMemberActivity.class);
                Home_teacherActivity.this.startActivity(intent);
            }
        });
    }
}