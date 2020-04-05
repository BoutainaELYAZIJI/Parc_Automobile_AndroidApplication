package com.led.parcapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ShowSingleVehiculeRecordActivity extends AppCompatActivity {

    HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;

    // Http Url For Filter vehicule Data from Id Sent from previous activity.
    String HttpURL = "http://192.168.1.105/LoginRegister/FilterVehiculeData.php";

    // Http URL for delete Already Open vehicule Record.
    String HttpUrlDeleteRecord = "http://192.168.1.105/LoginRegister/Deletevehicule.php";

    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();
    String FinalJSonObject ;
    String ImatriculHolder, CategHolder, MarqueHolder,MarqueCarbHolder;
    Button UpdateButton, DeleteButton;
    String TempItem;
    ProgressDialog progressDialog2;
    TextView Immatriculation,Categorie,Marque, MarquedeCarburant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_vehicule_record);
        Immatriculation = (TextView) findViewById(R.id.Imatriculation);
        Categorie = (TextView) findViewById(R.id.Categorie);
        Marque = (TextView) findViewById(R.id.Marque);
        MarquedeCarburant = (TextView) findViewById(R.id.Marquedecarburant);

        UpdateButton = (Button)findViewById(R.id.buttonUpdate);
        DeleteButton = (Button)findViewById(R.id.buttonDelete);

        //Receiving the ListView Clicked item value send by previous activity.
        TempItem = getIntent().getStringExtra("ListViewValue");

        //Calling method to filter vehicules Record and open selected record.
        HttpWebCall(TempItem);

        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ShowSingleVehiculeRecordActivity.this,UpdateActivity.class);

                // Sending Student Id, Name, Number and Class to next UpdateActivity.
                intent.putExtra("Id", TempItem);
                intent.putExtra("Immatriculation", ImatriculHolder);
                intent.putExtra("Categorie", CategHolder);
                intent.putExtra("Marque", MarqueHolder);
                intent.putExtra("MarquedeCarburant", MarqueCarbHolder);
                startActivity(intent);

                // Finishing current activity after opening next activity.
                finish();

            }
        });

        // Add Click listener on Delete button.
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling vehicule delete method to delete current record using Student ID.
               VehiculeDelete(TempItem);

            }
        });

    }

    // Method to Delete vehicule Record
    public void VehiculeDelete(final String vehiculeID) {

        class VehiculeDeleteClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog2 = ProgressDialog.show(ShowSingleVehiculeRecordActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog2.dismiss();

                Toast.makeText(ShowSingleVehiculeRecordActivity.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

                finish();

            }

            @Override
            protected String doInBackground(String... params) {

                // Sending vehicule id.
                hashMap.put("vehiculeID", params[0]);

                finalResult = httpParse.postRequest(hashMap, HttpUrlDeleteRecord);

                return finalResult;
            }
        }

        VehiculeDeleteClass vehiculeDeleteClass = new VehiculeDeleteClass();

        vehiculeDeleteClass.execute(vehiculeID);
    }


    //Method to show current record Current Selected Record
    public void HttpWebCall(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(ShowSingleVehiculeRecordActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(ShowSingleVehiculeRecordActivity.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("vehiculeID",params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURL);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }


    // Parsing Complete JSON Object.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            try
            {
                if(FinalJSonObject != null)
                {

                    Log.d("TO_VERIFY",FinalJSonObject);


                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject jsonObject;

                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);

                            // Storing information into Variables.
                            ImatriculHolder = jsonObject.getString("Immatriculation");
                            CategHolder = jsonObject.getString("Categorie");
                            MarqueHolder = jsonObject.getString("Marque");
                            MarqueCarbHolder = jsonObject.getString("MarquedeCarburant");
                        }
                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                else{
                    Log.d("TO_VERIFY","It's null !");
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {

            // Setting info into TextView after done all process .

            Immatriculation.setText(ImatriculHolder);
            Categorie.setText(CategHolder);
            Marque.setText(MarqueHolder);
            MarquedeCarburant.setText(MarqueCarbHolder);

        }
    }

}
