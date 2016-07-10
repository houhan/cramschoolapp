package com.example.user.a0705;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = (EditText) findViewById(R.id.editID);
        EditText password = (EditText) findViewById(R.id.editPWD);

        // 取得帳號密碼
        String strUserName = username.getText().toString();
        String strPassword = password.getText().toString();

        Button button = (Button) findViewById(R.id.button);//取得按鈕

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HomeActivity.class);
                MainActivity.this.startActivity(intent);


            }
        });//將這個Listener綑綁在這個Button


    }
}