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
import com.example.attenfancestudent.Until.Server;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText euser,epassword;
    private String user, password;
    Button loginbtn;
    TextView registrationnow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();
    }

    private void Anhxa() {
        euser = (EditText) findViewById(R.id.username);
        epassword = (EditText) findViewById(R.id.password);
        loginbtn =(Button) findViewById(R.id.loginbtn);
        registrationnow =(TextView) findViewById(R.id.registration);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = password ="";
                user = euser.getText().toString().trim();
                password = epassword.getText().toString().trim();
                if(!user.equals("") && !password.equals("")){
                    StringRequest stringRequest = new StringRequest( Request.Method.POST, Server.Getdatalogin, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("1")) {
                                Intent intent = new Intent( Login.this, MainActivity.class );
                                intent.putExtra("name",user);
                                startActivity( intent );
                                finish();
                            } else if (response.equals( "0" )) {
                                Toast.makeText( Login.this, "Please check your account or password", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText( Login.this, error.toString().trim(), Toast.LENGTH_SHORT ).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String , String> data = new HashMap<>();
                            data.put( "Account",user );
                            data.put( "Password",password );
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue( getApplicationContext() );
                    requestQueue.add( stringRequest );
                }else{
                    Toast.makeText(Login.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        registrationnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Registration.class));
            }
        });
    }
}