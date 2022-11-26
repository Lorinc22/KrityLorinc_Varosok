package com.example.Varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonListazas;
    private Button buttonUjFelvetel;

    public static String BASE_URL = "https://retoolapi.dev/QOVoYu/varosok";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonListazas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });

        buttonUjFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });
    }

    private void init()
    {
        buttonListazas = findViewById(R.id.buttonListazas);
        buttonUjFelvetel = findViewById(R.id.buttonUjFelvetel);
    }



}