package com.example.user.a0705;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.a0705.volleymgr.NetworkManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QkActivity extends AppCompatActivity {

    private EditText DateInput,NameInput,ResonInput,PSInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//回前頁

        //bData.putString("date", qk_time.getText().toString())
       // DateInput = (Button) findViewById(R.id.qk_time);
        NameInput = (EditText) findViewById(R.id.qk_name);
        ResonInput = (EditText) findViewById(R.id.qk_reson);
        PSInput = (EditText) findViewById(R.id.qk_PS);

        Button QkButton = (Button) findViewById(R.id.button2);
       // Button DateButton = (Button) findViewById(R.id.qk_time);
     //   String strDate = DateButton.getText().toString();
        QkButton.setOnClickListener(QkListener);
    }


        private View.OnClickListener QkListener = new View.OnClickListener() {

            public void onClick(View v) {

                try {
                    Button DateButton = (Button) findViewById(R.id.qk_time);

                    //螢幕擷取三項資料後上傳DB
                    String strDate = URLEncoder.encode(DateButton.getText().toString(),"UTF-8");
                    String strName = URLEncoder.encode(NameInput .getEditableText().toString(), "UTF-8");
                    String strReson = URLEncoder.encode(ResonInput.getEditableText().toString(), "UTF-8");
                    String strPS = URLEncoder.encode(PSInput.getEditableText().toString(), "UTF-8");

                    String url = "https://cramschoollogin.herokuapp.com/api/insertqk?name=" + strName + "&date=" + strDate +  "&reson=" + strReson +  "&PS=" + strPS ;
                    StringRequest request = new StringRequest(Request.Method.GET, url, mOnAddSuccessListener, mOnErrorListener);
                    NetworkManager.getInstance(QkActivity.this).request(null, request);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        };

    protected Response.Listener<String> mOnAddSuccessListener = new Response.Listener<String>() {

        @Override
        public void onResponse(String response) {

            DateInput.setText("");
            NameInput.setText("");
            ResonInput.setText("");
            PSInput.setText("");
            Toast.makeText(QkActivity.this, "新增成功", Toast.LENGTH_LONG).show();

            //結束頁面

            Intent intent = new Intent();
            intent.setClass(QkActivity.this, HomeActivity.class);
            QkActivity.this.startActivity(intent);
        }
    };

    protected Response.ErrorListener mOnErrorListener = new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError err) {

            Toast.makeText(QkActivity.this, "Err " + err.toString(), Toast.LENGTH_LONG).show();
        }
    };

    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle bData = new Bundle();
        bData.putInt("view", v.getId());
        Button button = (Button) v;
        bData.putString("date", button.getText().toString());
        newFragment.setArguments(bData);
        newFragment.show(getSupportFragmentManager(), "日期挑選器");
    }

    //回前頁
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }
}
