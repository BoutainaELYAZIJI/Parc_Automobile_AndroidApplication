package com.led.parcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Result;

public class Register extends Activity {
    EditText EtEmail,EtPass,EtFName,EtLName,EtConfirmPass;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EtFName = findViewById(R.id.EtFName);
        EtLName = findViewById(R.id.EtLName);
        EtEmail = findViewById(R.id.EtEmail);
        EtPass = findViewById(R.id.EtPass);
        EtConfirmPass = findViewById(R.id.EtConfirmPass);
        btnRegister = findViewById(R.id.btnRegister);

    }
      public void regs(View view) {
                String Firstname,Lastname,Email, Password,Confirmpass;
                Firstname=EtFName.getText().toString();
                Lastname=EtLName.getText().toString();
                Email =EtEmail.getText().toString();
                Password =EtPass.getText().toString();
                Confirmpass=EtConfirmPass.getText().toString();

          if (Firstname.equals("")) {

              Toast.makeText(Register.this, "First Name Required", Toast.LENGTH_SHORT).show();

          }else if(Lastname.equals("")){

              Toast.makeText(Register.this, "Last Name Required", Toast.LENGTH_SHORT).show();

          }else if (Email.equals("")) {

              Toast.makeText(Register.this, "Email Required", Toast.LENGTH_SHORT).show();
          } else if (Password.equals("")) {
              Toast.makeText(Register.this, "Password Required", Toast.LENGTH_SHORT).show();
          } else if (Confirmpass.equals("")) {
              Toast.makeText(Register.this, "Password Required", Toast.LENGTH_SHORT).show();
          }
          else if (!(Confirmpass).equals(Password)) {
              Toast.makeText(Register.this, "Mismatch", Toast.LENGTH_SHORT).show();
          }else {

              String method="register";
              TaskBackground taskBackground=new TaskBackground(this);
              taskBackground.execute(method,Firstname,Lastname,Email,Password,Confirmpass);


          }


      }



}
