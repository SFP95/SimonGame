package com.example.simon;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnComenzar, btnConfigurar;
    private Button btnRojo, btnVerde, btnAmarillo, btnAzul;
    private TextView tvTime, tvScore, tvHiScore;
    private int colores[]={Color.RED, Color.BLUE,Color.YELLOW,Color.GREEN};
    private int index=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asignartvs();
        asignarBotonesFuncioens();
        asignarBotonesColores();
        inhabilitarBotonesColores();
    }
    public void onComenzar(View view) {
        habilitarBotonesColores();
        reproducirColoresRandom();
    }

    private void reproducirColoresRandom() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < colores.length; i++) {
                        Thread.sleep(1000);
                        mostrarColor(colores[i]);
                        Thread.sleep(8000);
                        ocultarColores();
                        Thread.sleep(1000);
                        habilitarBotonesColores();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "TU TURNO", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        thread.start();
    }
    private void mostrarColor(final int color) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (color) {
                    case Color.RED:
                        btnRojo.setBackgroundColor(color);
                        break;
                    case Color.BLUE:
                        btnAzul.setBackgroundColor(color);
                        break;
                    case Color.YELLOW:
                        btnAmarillo.setBackgroundColor(color);
                        break;
                    case Color.GREEN:
                        btnVerde.setBackgroundColor(color);
                        break;
                }
            }
        });
    }
    private void ocultarColores() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnAmarillo.setVisibility(INVISIBLE);
                btnAzul.setVisibility(INVISIBLE);
                btnVerde.setVisibility(INVISIBLE);
                btnRojo.setVisibility(INVISIBLE);
            }
        });
    }
    public void onConfigurar(View view) {
    }
    public void habilitarBotonesColores(){
        btnAmarillo.setVisibility(VISIBLE);
        btnAzul.setVisibility(VISIBLE);
        btnVerde.setVisibility(VISIBLE);
        btnRojo.setVisibility(VISIBLE);
    }
    public void inhabilitarBotonesColores(){
        btnAmarillo.setVisibility(INVISIBLE);
        btnAzul.setVisibility(INVISIBLE);
        btnVerde.setVisibility(INVISIBLE);
        btnRojo.setVisibility(INVISIBLE);
    }
    public void asignarBotonesFuncioens() {
        btnComenzar = findViewById(R.id.btnComenzar);
        btnConfigurar = findViewById(R.id.btnConfigurar);
    }
    public void asignarBotonesColores() {
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

    public void onRojo(View view) {
    }

    public void onAma(View view) {
    }

    public void onVerde(View view) {
    }

    public void onAzul(View view) {
    }


}
