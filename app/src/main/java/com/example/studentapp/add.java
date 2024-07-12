package com.example.studentapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class add extends AppCompatActivity {
    AppCompatButton b1,b2;
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    String apiurl= "https://courseapplogix.onrender.com/addstudents";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        b1= (AppCompatButton) findViewById(R.id.sub);
        b2= (AppCompatButton) findViewById(R.id.back);
        e1= (EditText) findViewById(R.id.fname);
        e2= (EditText) findViewById(R.id.lname);
        e3= (EditText) findViewById(R.id.col);
        e4= (EditText) findViewById(R.id.dob);
        e5= (EditText) findViewById(R.id.course);
        e6= (EditText) findViewById(R.id.mob);
        e7= (EditText) findViewById(R.id.email);
        e8= (EditText) findViewById(R.id.address);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1="",s2="",s3="",s4="",s5="",s6="",s7="",s8="";
                try {
                    s1 = e1.getText().toString();
                    s2 = e2.getText().toString();
                    s3 = e3.getText().toString();
                    s4 = e4.getText().toString();
                    s5 = e5.getText().toString();
                    s6 = e6.getText().toString();
                    s7 = e7.getText().toString();
                    s8 = e8.getText().toString();
                    if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")||s6.equals("")||s7.equals("")||s8.equals("")){
                        Toast.makeText(getApplicationContext(),"please fill all the details",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //Toast.makeText(getApplicationContext(),"student added successfully",Toast.LENGTH_SHORT).show();
                        JSONObject student = new JSONObject();
                        try {
                            student.put("firstname",s1);
                            student.put("lastname",s2);
                            student.put("college",s3);
                            student.put("dob",s4);
                            student.put("course",s5);
                            student.put("mobile",s6);
                            student.put("email",s7);
                            student.put("address",s8);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        JsonObjectRequest request = new JsonObjectRequest(
                                Request.Method.POST,
                                apiurl,
                                student,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(getApplicationContext(), "added successfully", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                        //request queue
                        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(request);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}