package com.example.simon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simon.ConfigActivity;
import com.example.simon.GameActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnComenzar;
    private Button btnConfigurar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnComenzar = findViewById(R.id.btnComenzar);
        btnConfigurar = findViewById(R.id.btnConfigurar);

        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnComenzar.setVisibility(View.INVISIBLE);
                btnConfigurar.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        btnConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                startActivity(intent);
            }
        });
    }
}
