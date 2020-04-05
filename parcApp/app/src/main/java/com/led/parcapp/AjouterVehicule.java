package com.led.parcapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class AjouterVehicule extends AppCompatActivity {
    EditText Immatriculation,Categorie,Marque, MarquedeCarburant;
    Button btnRegisterVhc,btnShowVhc;
    String ImatriculHolder, CategHolder, MarqueHolder,MarqueCarbHolder;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://192.168.1.105/LoginRegister/AddNewVehicule.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_vehicule);

        Immatriculation = findViewById(R.id.EtImmatricul);
        Categorie = findViewById(R.id.EtCateg);
        Marque = findViewById(R.id.EtMarque);
        MarquedeCarburant = findViewById(R.id.EtMarqueCarb);
        btnRegisterVhc = findViewById(R.id.btnRegisterVhc);
        btnShowVhc= findViewById(R.id.btnShowVhc);
        btnRegisterVhc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    VehiculeRegistration(ImatriculHolder,CategHolder, MarqueHolder,MarqueCarbHolder);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(AjouterVehicule.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });

        btnShowVhc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), ShowAllVehiculeActivity.class);

                startActivity(intent);

            }
        });
    }

    public void VehiculeRegistration(final String V_Immatriculation, final String V_Categorie, final String V_Marque,final String V_Marquedecarburant){

        class VehiculeRegistrationClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(AjouterVehicule.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(AjouterVehicule.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("Immatriculation",params[0]);

                hashMap.put("Categorie",params[1]);

                hashMap.put("Marque",params[2]);

                hashMap.put("Marquedecarburant",params[3]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

       VehiculeRegistrationClass studentRegistrationClass = new VehiculeRegistrationClass();

        studentRegistrationClass.execute(V_Immatriculation,V_Categorie,V_Marque,V_Marquedecarburant);
    }


    public void CheckEditTextIsEmptyOrNot(){

        ImatriculHolder= Immatriculation.getText().toString();
        CategHolder = Categorie.getText().toString();
        MarqueHolder= Marque.getText().toString();
        MarqueCarbHolder= MarquedeCarburant.getText().toString();

        if(TextUtils.isEmpty(ImatriculHolder) || TextUtils.isEmpty(CategHolder) || TextUtils.isEmpty(MarqueHolder)|| TextUtils.isEmpty(MarqueCarbHolder))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }
}
