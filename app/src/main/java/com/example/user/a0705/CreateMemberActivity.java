package com.example.user.a0705;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.android.volley.Response;
import com.example.user.a0705.volleymgr.NetworkManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class CreateMemberActivity extends AppCompatActivity {


    private EditText AccountInput,PasswordInput,NameInput;
    private ProgressDialog mProgressDialog;
    private String RegID;

/*
    private static final String TAG = "CreateMemberActivity";
    private static final String SENDER_ID = "329379982081";
    private static final String API_SERVER = "AIzaSyAPiA-VHuwcp2Wd_7XuziOlrXf5pBHO0vc";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_member);

        AccountInput = (EditText) findViewById(R.id.createid);
        PasswordInput = (EditText) findViewById(R.id.createpwd);
        NameInput = (EditText) findViewById(R.id.createname);

        Button CreateButton = (Button) findViewById(R.id.create);
        CreateButton.setOnClickListener(CreateMemberListener);
/*
        String currentRegId = getGcmRegId();
        if (TextUtils.isEmpty(currentRegId)) {
            registration();
        }*/

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Wait...");

    }

    /*
    // ----------------- 測試GCM
    public String getGcmRegId() {
        return PreferenceManager.getDefaultSharedPreferences(this).getString("registration_id", null);
    }
    public void registration() {
        GCMRegistrationTask task = new GCMRegistrationTask();
        task.execute();
    }
    private class GCMRegistrationTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            Log.d(TAG, "Registering");
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
            try {
                return gcm.register(SENDER_ID);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                RegID = result;
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////
    */
    private OnClickListener CreateMemberListener = new OnClickListener() {

        public void onClick(View v) {

            try {

                //螢幕擷取三項資料後上傳DB
                String strAccount = URLEncoder.encode(AccountInput.getEditableText().toString(), "UTF-8");
                String strPassword = URLEncoder.encode(PasswordInput.getEditableText().toString(), "UTF-8");
                String strName = URLEncoder.encode(NameInput.getEditableText().toString(), "UTF-8");
                mProgressDialog.show();

                String url = "https://cramschoollogin.herokuapp.com/api/insert?user=" + strAccount + "&password=" + strPassword +  "&name=" + strName ;
                StringRequest request = new StringRequest(Request.Method.GET, url, mOnAddSuccessListener, mOnErrorListener);
                NetworkManager.getInstance(CreateMemberActivity.this).request(null, request);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    };

    protected Response.Listener<String> mOnAddSuccessListener = new Response.Listener<String>() {

        @Override
        public void onResponse(String response) {
            mProgressDialog.dismiss();
            AccountInput.setText("");
            PasswordInput.setText("");
            NameInput.setText("");
            Toast.makeText(CreateMemberActivity.this, "新增成功", Toast.LENGTH_LONG).show();

            //結束頁面
            CreateMemberActivity.this.finish();
        }
    };

    protected ErrorListener mOnErrorListener = new ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError err) {
            mProgressDialog.dismiss();
            Toast.makeText(CreateMemberActivity.this, "Err " + err.toString(), Toast.LENGTH_LONG).show();
        }
    };



}

