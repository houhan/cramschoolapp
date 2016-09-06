package com.example.user.a0705;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

import android.content.Intent;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.a0705.volleymgr.ContentTest;
import com.example.user.a0705.volleymgr.NetworkManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BillboardActivity extends HomeActivity {


    private ArrayList<ContentTest> contentTest = new ArrayList<ContentTest>();
    private ListView listView;
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billboard);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//回前頁

        StringRequest request = new StringRequest(Request.Method.GET, "https://cramschoollogin.herokuapp.com/api/querybillboard", mResponseListener, mErrorListener);
        NetworkManager.getInstance(this).request(null, request);

       /* Button button = (Button) findViewById(R.id.buttonAdd);//取得按鈕
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BillboardActivity.this, AddBillboardActivity.class);
                startActivity(intent);
            }
        });//將這個Listener綑綁在這個Button  */


        //設定此Activity使用的res layout
        setContentView(R.layout.list);
        listView = (ListView) findViewById(R.id.listView);
        myAdapter = new MyAdapter(this, contentTest);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

 /*       Button DeleteButton = (Button) findViewById(R.id.deletebb);
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.GET, "https://cramschoollogin.herokuapp.com/api/delete2" , mOnDeleteSuccessListener, mOnErrorListener);
                NetworkManager.getInstance(BillboardActivity.this).request(null, request);
            }
        });*/

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(BillboardActivity.this)
                        .setTitle("want to delele?")
                        .setMessage("Want to delete " + position + " item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myAdapter.removeItem(position);
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

                return false;
            }
        });
    }
    void fillData() {
    }


    private Response.Listener<String> mResponseListener = new Response.Listener<String>() {

        @Override
        public void onResponse(String string) {
            Log.d("Response", string);
            // contentTest=new ArrayList<ContentTest>();
            try {

                JSONArray ary = new JSONArray(string);
                StringBuilder dates = new StringBuilder();
                StringBuilder titles = new StringBuilder();
                StringBuilder contents = new StringBuilder();
                for (int i = 0; i < ary.length(); i++) {
                    JSONObject json = ary.getJSONObject(i);
                    String date = json.getString("date");
                    String title = json.getString("title");
                    String content = json.getString("content");
                    ContentTest contentS = new ContentTest(date, title, content);
                    contentTest.add(contentS);
                }
                myAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Error", error.toString());
        }
    };

/*
    private Response.Listener<String> mOnDeleteSuccessListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
        }
    };
    private ErrorListener mOnErrorListener = new ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError err) {
            Toast.makeText(BillboardActivity.this, err.toString(), Toast.LENGTH_LONG).show();
        }
    };*/


    //回前頁
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }


}