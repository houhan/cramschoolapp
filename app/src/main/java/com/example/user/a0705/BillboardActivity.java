package com.example.user.a0705;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BillboardActivity extends HomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billboard);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);

        setResult(RESULT_OK, intent);

        finish();
    }

}
