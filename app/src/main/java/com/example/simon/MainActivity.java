package com.example.simon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnComenzar, btnConfigurar;
    private Button btnRojo, btnVerde, btnAmarillo, btnAzul;
    private TextView tvTime, tvScore, tvHiScore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asignartvs();
        asignarBotonesFuncioens();
        asignarYcolorearBotonesColores();

    }

    public void asignarBotonesFuncioens() {
        btnComenzar = findViewById(R.id.btnComenzar);
        btnConfigurar = findViewById(R.id.btnConfigurar);
    }
    public void asignarYcolorearBotonesColores() {
        btnRojo = findViewById(R.id.btnRojo);
        btnAmarillo = findViewById(R.id.btnAmarillo);
        btnAzul = findViewById(R.id.btnAzul);
        btnVerde = findViewById(R.id.btnVerde);
    }
    public void asignartvs(){
        tvScore=findViewById(R.id.tvScore);
        tvHiScore=findViewById(R.id.tvhiScore);
        tvTime=findViewById(R.id.tvTime);
    }
}
