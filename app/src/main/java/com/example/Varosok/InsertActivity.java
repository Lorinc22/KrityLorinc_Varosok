package com.example.Varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

public class InsertActivity extends AppCompatActivity {
    private Button insertButtonVissza;
    private Button insertButtonFelvetel;
    private EditText insertEditTextNev;
    private EditText insertEditTextOrszag;
    private EditText insertEditTextLakossag;
    private String BASE_URL = "https://retoolapi.dev/RXSwhh/varosok";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();

        insertButtonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(InsertActivity.this, MainActivity.class);
                InsertActivity.this.startActivity(myIntent);
            }
        });

        insertButtonFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVaros();
            }
        });
    }

    public void init(){
        insertButtonVissza = findViewById(R.id.insertButtonVissza);
        insertButtonFelvetel = findViewById(R.id.insertButtonFelvetel);
        insertEditTextNev = findViewById(R.id.insertEditTextNev);
        insertEditTextOrszag = findViewById(R.id.insertEditTextOrszag);
        insertEditTextLakossag = findViewById(R.id.insertEditTextLakossag);
    }

    private void addVaros() {
        String name = insertEditTextNev.getText().toString();
        String country = insertEditTextOrszag.getText().toString();
        String population = insertEditTextLakossag.getText().toString();

        boolean valid = validation();

        if (valid){
            Toast.makeText(this,
                    "Tölts ki Mindent!", Toast.LENGTH_SHORT).show();
            return;
        }

        int populationInt = Integer.parseInt(population);
        City city = new City(0,name,country,populationInt);
        Gson jsonConverter = new Gson();
        RequestTask task = new RequestTask(BASE_URL, "POST",
                jsonConverter.toJson(city));
        task.execute();
    }

    private boolean validation() {
        if (insertEditTextNev.getText().toString().isEmpty() ||
                insertEditTextOrszag.getText().toString().isEmpty() || insertEditTextLakossag.getText().toString().isEmpty())
            return true;
        else
            return false;
    }

    private class RequestTask extends AsyncTask<Void, Void, Response> {
        private String requestUrl;
        private String requestMethod;
        private String requestBody;

        public RequestTask(String requestUrl) {
            this.requestUrl = requestUrl;
            this.requestMethod = "GET";
        }

        public RequestTask(String requestUrl, String requestMethod) {
            this.requestUrl = requestUrl;
            this.requestMethod = requestMethod;
        }

        public RequestTask(String requestUrl, String requestMethod, String requestBody) {
            this.requestUrl = requestUrl;
            this.requestMethod = requestMethod;
            this.requestBody = requestBody;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                switch (requestMethod) {
                    case "GET":
                        response = RequestHandler.get(BASE_URL);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestBody);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestBody);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

    }
}