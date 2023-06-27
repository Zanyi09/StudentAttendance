package com.example.attenfancestudent.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.attenfancestudent.R;
import com.example.attenfancestudent.Until.Checkconnect;
import com.example.attenfancestudent.Until.Server;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    EditText eusername, epassword,erepassword,eemail;
    private String username, password,repassword,email;
    Button sigupbtn;
    TextView loginnow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Anhxa();
    }

    private void Anhxa() {
        eusername = (EditText) findViewById(R.id.username1);
        eemail = (EditText) findViewById(R.id.email);
        epassword = (EditText) findViewById(R.id.password1);
        erepassword = (EditText) findViewById(R.id.repassword);
        sigupbtn = (Button) findViewById(R.id.signupbtn);
        loginnow = (TextView) findViewById(R.id.loginnow);

        loginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this,Login.class));
            }
        });
        sigupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = email = password = repassword ="";
                username = eusername.getText().toString().trim();
                email = eemail.getText().toString().trim();
                password = epassword.getText().toString().trim();
                repassword = erepassword.getText().toString().trim();
                if(!password.equals(repassword)){
                    Checkconnect.Showmessage(getApplicationContext(),"Password and repassword incorrect");
                } else if (!username.equals("") && !email.equals("") && !password.equals("")) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Getdataregistration, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("1")) {
                                Checkconnect.Showmessage(getApplicationContext(),"Sign Up Success !!!");
                                sigupbtn.setClickable(false);
                            } else if (response.equals("0")) {
                                Checkconnect.Showmessage(getApplicationContext(),"Registration failed !!!");
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText( getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT ).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String , String> data = new HashMap<>();
                            data.put( "Account",username );
                            data.put( "Email",email );
                            data.put( "Password",password );
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue( getApplicationContext() );
                    requestQueue.add( stringRequest );
                }
            }
        });
    }
}