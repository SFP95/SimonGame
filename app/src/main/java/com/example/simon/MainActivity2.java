package com.example.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity2 extends AppCompatActivity {
    EditText edTiempo, edPersistencia, edEsperaColores;
    RadioGroup rgSonido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edTiempo= findViewById(R.id.edTiempoJuego);
        edPersistencia= findViewById(R.id.edPersistencia);
        edEsperaColores= findViewById(R.id.edEsperaColores);

        String t= String.valueOf(edTiempo.getText());
        String per= String.valueOf(edPersistencia.getText());
        String espCol = String.valueOf(edEsperaColores.getText());

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("timepo",t);
        i.putExtra("persistencia",per);
        i.putExtra("esperaColores",espCol);

    }

    public void onReset(View view) {
        String puntuacion= "0";
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("reset",puntuacion);
    }


    public void onVolver(View view) {
        int SelectId = rgSonido.getCheckedRadioButtonId();
        boolean enableSound = SelectId == R.id.checkSi;
        Intent intent = new Intent();
        intent.putExtra("ENABLE_SOUND", enableSound);
        setResult(MainActivity.RESULT_OK, intent);
        finish();
    }
}