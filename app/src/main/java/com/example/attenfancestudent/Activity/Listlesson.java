package com.example.attenfancestudent.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.attenfancestudent.Adapter.LessonAdapter;
import com.example.attenfancestudent.Model.Date;
import com.example.attenfancestudent.Model.Lesson;
import com.example.attenfancestudent.Model.Student;
import com.example.attenfancestudent.R;
import com.example.attenfancestudent.Until.Checkconnect;
import com.example.attenfancestudent.Until.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Listlesson extends AppCompatActivity {
    Toolbar toolbarlesson;
    TextView name_class;
    RecyclerView recyclerViewlesson;
    ArrayList<String> listdate;
    //String [] item = {"29/12/2001","05/11/2023","28/12/2000"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapteritem;
    ArrayList<Lesson> arraylesson;
    LessonAdapter lessonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listlesson);

        Anhxa();
        getDate();
        getdatalesson();
    }

    private void getdatalesson() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Getdatalesson, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String item = adapterView.getItemAtPosition(i).toString();
                            //Toast.makeText(getApplicationContext(),"Date:"+item, Toast.LENGTH_SHORT).show();
                            int Id_lesson;
                            String Stime;
                            String Etime;
                            String Ldate;
                            int Id_teacher;
                            String N_teacher;
                            int Id_subject;
                            String Lclass;
                            Intent intent = getIntent();
                            String nclass_lesson = intent.getStringExtra("thongtinsubject");
                            for(i =0; i<response.length(); i++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    Id_lesson = jsonObject.getInt("Lesson_id");
                                    Stime = jsonObject.getString("Time_start");
                                    Etime = jsonObject.getString("Time_end");
                                    Ldate = jsonObject.getString("Date");
                                    Id_teacher = jsonObject.getInt("Teacher_id");
                                    N_teacher = jsonObject.getString("Name");
                                    Id_subject = jsonObject.getInt("Subject_id");
                                    Lclass = jsonObject.getString("Class");
                                    if(nclass_lesson.equals(Lclass)){
                                        if(item.equals(Ldate)){
                                            arraylesson.add(new Lesson(Id_lesson, Stime, Etime, Ldate, Id_teacher, N_teacher, Id_subject, Lclass));
                                            lessonAdapter.notifyDataSetChanged();
                                        }

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
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

    private void getDate() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.getDate, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            if(response !=null){
                String Date ;
                for (int i =0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Date = jsonObject.getString("Date");
                        listdate.add(Date);
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
        toolbarlesson = (Toolbar)findViewById(R.id.toolbarlesson);
        name_class = (TextView)findViewById(R.id.nameclass_lesson);
        autoCompleteTextView =(AutoCompleteTextView) findViewById(R.id.auto_complete_txt);
        recyclerViewlesson = (RecyclerView)findViewById(R.id.recyclerviewlesson);

        // get json lesson

        arraylesson = new ArrayList<>();
        lessonAdapter = new LessonAdapter(getApplicationContext(),arraylesson);
        recyclerViewlesson.setHasFixedSize(true);
        recyclerViewlesson.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerViewlesson.setAdapter(lessonAdapter);
      //  recyclerViewlesson.setAdapter(lessonAdapter);
        // get class second activity
        Intent intent = getIntent();
        String nclass_lesson = intent.getStringExtra("thongtinsubject");
        name_class.setText(nclass_lesson);
        listdate = new ArrayList<>();
        adapteritem = new ArrayAdapter<String>(this,R.layout.list_item,listdate);
        autoCompleteTextView.setAdapter(adapteritem);

//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(),"Date:"+item, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}