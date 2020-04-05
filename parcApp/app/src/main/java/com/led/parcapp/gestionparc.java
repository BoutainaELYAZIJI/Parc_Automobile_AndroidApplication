package com.led.parcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class gestionparc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionparc);
    }

    public void ChauffeursOnClick(View view) {
        Intent i = new Intent(gestionparc.this, AjouterChauffeur.class);
        startActivity(i);

    }

    public void VehiculesOnClick(View view){
        Intent i = new Intent(gestionparc.this, AjouterVehicule.class);
        startActivity(i);

    }

    public void MissionsOnClick(View view){
        Intent i = new Intent(gestionparc.this, AjouterMission.class);
        startActivity(i);
    }

    public void QuiterOnClick(View view){
        finish();

    }


}
