package com.example.attenfancestudent.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.attenfancestudent.Adapter.StudentAdapter;
import com.example.attenfancestudent.Model.Student;
import com.example.attenfancestudent.R;
import com.example.attenfancestudent.Until.Checkconnect;
import com.example.attenfancestudent.Until.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListStudent extends AppCompatActivity {
    TextView nameclass;
    TextView dateclass;
    RecyclerView recyclerViewstudent;
    ArrayList<Student> studentlist;
    StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);

        Anhxa();
        if (Checkconnect.haveNetworkConnection(getApplicationContext())){
            Getdatastudent();
        }else {
            Checkconnect.Showmessage(getApplicationContext(),"Check connection");
            finish();
        }
    }

    private void Getdatastudent() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Getdatastudent, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            if(response != null){
                int st_id;
                String st_name;
                String st_class;
                int lesson_id;
                String status;
                Intent intent = getIntent();
                int id_lesson = intent.getIntExtra("idlesson",0);
                for(int i =0; i <response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        st_id = jsonObject.getInt("Student_id");
                        st_name = jsonObject.getString("Name");
                        st_class = jsonObject.getString("Class");
                        lesson_id = jsonObject.getInt("Lesson_id");
                        status = jsonObject.getString("AttendanceStatus");
                        if(lesson_id == id_lesson){
                            studentlist.add( new Student(st_id,st_name,st_class,lesson_id,status));
                            studentAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Checkconnect.Showmessage(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void Anhxa() {
        nameclass = (TextView) findViewById(R.id.nameclass_student);
        dateclass = (TextView) findViewById(R.id.dateclass);
        recyclerViewstudent = (RecyclerView) findViewById(R.id.recyclerviewstudent);
        // get class
        Intent intent = getIntent();
        String datelesson = intent.getStringExtra("datelesson");
        dateclass.setText(datelesson);
        String namelesson = intent.getStringExtra("classlesson");
        nameclass.setText(namelesson);
        //get date
        studentlist = new ArrayList<>();
        studentAdapter = new StudentAdapter(getApplicationContext(),studentlist);
        recyclerViewstudent.setHasFixedSize(true);
        recyclerViewstudent.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerViewstudent.setAdapter(studentAdapter);

    }
}