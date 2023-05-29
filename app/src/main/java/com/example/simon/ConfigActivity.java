package com.example.simon;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {
    private EditText etTiempoJuego;
    private EditText etTiempoPersistencia;
    private EditText etTiempoEspera;
    private CheckBox cbSonidos;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        etTiempoJuego = findViewById(R.id.etTiempoJuego);
        etTiempoPersistencia = findViewById(R.id.etTiempoPersistencia);
        etTiempoEspera = findViewById(R.id.etTiempoEspera);
        cbSonidos = findViewById(R.id.cbSonidos);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario
                int tiempoJuego = Integer.parseInt(etTiempoJuego.getText().toString());
                int tiempoPersistencia = Integer.parseInt(etTiempoPersistencia.getText().toString());
                int tiempoEspera = Integer.parseInt(etTiempoEspera.getText().toString());
                boolean sonidosActivados = cbSonidos.isChecked();

                // Guardar la configuración en SharedPreferences o en cualquier otro medio de almacenamiento
                // y realizar cualquier otra acción necesaria
            }
        });
    }
}
