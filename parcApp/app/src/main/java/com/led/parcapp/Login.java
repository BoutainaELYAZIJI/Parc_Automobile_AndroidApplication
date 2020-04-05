package com.led.parcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity  {


    EditText EtEmail,EtPass;
    Button btnLogin;
    TextView TvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_login);

           EtEmail=findViewById(R.id.EtEmail);
           EtPass=findViewById(R.id.EtPass);
           btnLogin=findViewById(R.id.btnLogin);
           TvRegister=findViewById(R.id.TvRegister);
    }

    public void Login(View view) {

        String email, pass;
        email = EtEmail.getText().toString();
        pass = EtPass.getText().toString();

        if (email.equals("")) {

            Toast.makeText(Login.this, "Email Required", Toast.LENGTH_SHORT).show();
        } else if (pass.equals("")) {

            Toast.makeText(Login.this, "Password Required", Toast.LENGTH_SHORT).show();

        }else{

            String method="Login";
            TaskBackground taskBackground=new TaskBackground(this);
            taskBackground.execute(method,email,pass);

        }


    }



     public void TvRegister(View view) {
                    Intent i = new Intent(Login.this,Register.class);
                    startActivity(i);
                    finish();
                }



}



