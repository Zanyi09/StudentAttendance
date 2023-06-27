package com.example.attenfancestudent.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.attenfancestudent.Adapter.SubjectAdapter;
import com.example.attenfancestudent.Model.Subject;
import com.example.attenfancestudent.R;
import com.example.attenfancestudent.Until.Checkconnect;
import com.example.attenfancestudent.Until.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmain;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;

    ArrayList<Subject> subjectList;
    SubjectAdapter subjectAdapter;
    TextView nameuserlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if (Checkconnect.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            ActionViewFlipper();
            Getlesson();
        }else {
            Checkconnect.Showmessage(getApplicationContext(),"Check connection");
            finish();
        }
    }
    private void Getlesson() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Getdataclass, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int Id =0;
                    String Namejc ="";
                    String Classjc ="";
                    for (int i =0; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Id = jsonObject.getInt("Subject_id");
                            Namejc = jsonObject.getString("Subject_name");
                            Classjc = jsonObject.getString("Class");
                           subjectList.add(new Subject(Id,Namejc,Classjc));
                           subjectAdapter.notifyDataSetChanged();
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

    private void ActionViewFlipper() {
        ArrayList<String> slide = new ArrayList<>();
        slide.add("https://i.pinimg.com/564x/e8/8c/d4/e88cd4994a283c793a66bfa3fb6d1b6e.jpg");
        slide.add("https://i.pinimg.com/564x/27/f8/c6/27f8c6b44e011554388714ea70d22bf8.jpg");
        slide.add("https://i.pinimg.com/564x/35/39/27/3539272f8446ac133ecd2b7e765f3f54.jpg");
        slide.add("https://i.pinimg.com/564x/92/cf/9f/92cf9f0797928fbf1ca1b1e2655211d1.jpg");
        for (int i=0; i<slide.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(slide.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setAnimation(animation_in);
        viewFlipper.setAnimation(animation_out);


    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ellipsis);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarmain);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        recyclerViewmain = (RecyclerView) findViewById(R.id.recyclerviewmain);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        listView = (ListView) findViewById(R.id.listviewmain);
        drawerLayout =(DrawerLayout) findViewById(R.id.drawerlayout);
        nameuserlogin =(TextView)findViewById(R.id.nameuserlogin);
        // move date for second activity
        Intent intent = getIntent();
        String namelogin =  intent.getStringExtra("name");
        nameuserlogin.setText("Hello,  "+namelogin);
        // list subject
        subjectList = new ArrayList<>();
        subjectAdapter = new SubjectAdapter(getApplicationContext(),subjectList);


        recyclerViewmain.setHasFixedSize(true);
        recyclerViewmain.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmain.setAdapter(subjectAdapter);
    }
}