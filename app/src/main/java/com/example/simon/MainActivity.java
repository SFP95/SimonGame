package com.example.simon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnComenzar, btnConfigurar;
    private Button btnRojo, btnVerde, btnAmarillo, btnAzul;
    private TextView tvTime, tvScore, tvHiScore;

    private List<Integer> secuenciaAleatoria;
    private List<Integer> secuenciaUsuario;
    private int puntuacion;
    private int remainingTime = 60;
    private CountDownTimer countDownTimer;
    private int tiempoPersistenciaColor = 500;
    private int tiempoEsperaColor = 50;
    private int puntuacionMaxima;
    private boolean sonidosActivados;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asignarVistas();

        inhabilitarBotonesColores();

        // Cargar la puntuación máxima guardada
        puntuacionMaxima = obtenerPuntuacionMaxima();

        // Configurar el juego inicialmente
        configurarJuego();
    }
    public void onComenzar(View view) {
        iniciarJuego();
        IniciarTiempo();
    }

    private void IniciarTiempo() {
        countDownTimer = new CountDownTimer(remainingTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime--;
                tvTime.setText(String.valueOf(remainingTime));
                if (remainingTime == 0) {
                    cancel();
                    tvTime.setText("0");
                    Toast.makeText(this"TIEMPO AGOTADO",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFinish() {
                // No se ejecutará en este caso
            }
        }.start();
    }
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
    public void onConfigurar(View view) {
        abrirActividadConfiguracion();
    }

    public void onAma(View view) { registrarRespuesta(Color.YELLOW); }

    public void onRojo(View view) {registrarRespuesta(Color.RED);}

    public void onVerde(View view) { registrarRespuesta(Color.GREEN);}

    public void onAzul(View view) { registrarRespuesta(Color.BLUE);}

    private void asignarVistas() {
        btnComenzar = findViewById(R.id.btnComenzar);
        btnConfigurar = findViewById(R.id.btnConfigurar);
        btnRojo = findViewById(R.id.btnRojo);
        btnAmarillo = findViewById(R.id.btnAmarillo);
        btnAzul = findViewById(R.id.btnAzul);
        btnVerde = findViewById(R.id.btnVerde);
        tvTime = findViewById(R.id.tvTime);
        tvScore = findViewById(R.id.tvScore);
        tvHiScore = findViewById(R.id.tvhiScore);
    }


    private void inhabilitarBotonesColores() {
        btnAmarillo.setVisibility(View.INVISIBLE);
        btnAzul.setVisibility(View.INVISIBLE);
        btnVerde.setVisibility(View.INVISIBLE);
        btnRojo.setVisibility(View.INVISIBLE);
    }

    private void habilitarBotonesColores() {
        btnAmarillo.setVisibility(View.VISIBLE);
        btnAzul.setVisibility(View.VISIBLE);
        btnVerde.setVisibility(View.VISIBLE);
        btnRojo.setVisibility(View.VISIBLE);
    }

    private void iniciarJuego() {  //AJUSTAR ESTO
        // Reiniciar la puntuación y las secuencias
        puntuacion = 0;
        secuenciaAleatoria = new ArrayList<>();
        secuenciaUsuario = new ArrayList<>();

        //1º
        ocultarColores();
        // Generar la primera secuencia aleatoria
        generarSecuenciaAleatoria();

        // Iniciar la reproducción de la secuencia
        reproducirSecuencia();

        // Mostrar el mensaje "TU TURNO" y habilitar los botones de colores
        Toast.makeText(MainActivity.this, "TU TURNO", Toast.LENGTH_SHORT).show();

        //ultimo
        habilitarBotonesColores();
    }

    private void generarSecuenciaAleatoria() { //POR REVISAR
        /*Random random = new Random();*/

        for (int i = 0; i < 4; i++) {
            int color = generarColorAleatorio();
            secuenciaAleatoria.add(color);
        }
    }

    private int generarColorAleatorio() {
        int[] colores = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};
        Random random = new Random();
        int indice = random.nextInt(colores.length);
        return colores[indice];
    }

    private void reproducirSecuencia() {  //REVISAR
        new CountDownTimer(secuenciaAleatoria.size() * (tiempoPersistenciaColor + tiempoEsperaColor), tiempoPersistenciaColor + tiempoEsperaColor) {
            int indiceColor = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                mostrarColor(secuenciaAleatoria.get(indiceColor));
                indiceColor++;
            }

            @Override
            public void onFinish() {
                ocultarColores();

                // Mostrar mensaje y habilitar los botones para que el usuario responda
                Toast.makeText(MainActivity.this, "TU TURNO", Toast.LENGTH_SHORT).show();
                habilitarBotonesColores();
            }
        }.start();
    }

    private void mostrarColor(int color) {
        // Mostrar el color cambiando el fondo del botón correspondiente
        if (color == Color.RED) {
            btnRojo.setBackgroundColor(color);
        } else if (color == Color.YELLOW) {
            btnAmarillo.setBackgroundColor(color);
        } else if (color == Color.BLUE) {
            btnAzul.setBackgroundColor(color);
        } else if (color == Color.GREEN) {
            btnVerde.setBackgroundColor(color);
        }
    }

    private void ocultarColores() {
        // Restaurar el fondo de los botones a su color original
        btnRojo.setBackgroundColor(Color.LTGRAY);
        btnAmarillo.setBackgroundColor(Color.LTGRAY);
        btnAzul.setBackgroundColor(Color.LTGRAY);
        btnVerde.setBackgroundColor(Color.LTGRAY);
    }

    private void registrarRespuesta(int color) {
        secuenciaUsuario.add(color);

        // Comprobar si se ha completado la secuencia del usuario
        if (secuenciaUsuario.size() == secuenciaAleatoria.size()) {
            compararSecuencias();
        }
    }

    private void compararSecuencias() {
        boolean secuenciasIguales = true;

        for (int i = 0; i < secuenciaAleatoria.size(); i++) {
            if (!secuenciaAleatoria.get(i).equals(secuenciaUsuario.get(i))) {
                secuenciasIguales = false;
                break;
            }
        }

        if (secuenciasIguales) {
            // Las secuencias son iguales
            Toast.makeText(MainActivity.this, "MUY BIEN, probemos ahora con 5", Toast.LENGTH_SHORT).show();
            puntuacion += Math.round(secuenciaAleatoria.size() * 0.7);

            // Generar la siguiente secuencia aleatoria
            generarSecuenciaAleatoria();

            // Reiniciar la secuencia del usuario
            secuenciaUsuario.clear();

            // Iniciar la reproducción de la siguiente secuencia
            reproducirSecuencia();
        } else {
            // Las secuencias no son iguales
            Toast.makeText(MainActivity.this, "Secuencia incorrecta. Vuelve a intentarlo.", Toast.LENGTH_SHORT).show();

            // Reproducir la última secuencia aleatoria no acertada
            reproducirUltimaSecuencia();
        }
    }

    private void reproducirUltimaSecuencia() {
        new CountDownTimer(secuenciaAleatoria.size() * (tiempoPersistenciaColor + tiempoEsperaColor), tiempoPersistenciaColor + tiempoEsperaColor) {
            int indiceColor = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                mostrarColor(secuenciaAleatoria.get(indiceColor));
                indiceColor++;
            }

            @Override
            public void onFinish() {
                ocultarColores();

                // Mostrar mensaje y habilitar los botones para que el usuario responda
                Toast.makeText(MainActivity.this, "TU TURNO", Toast.LENGTH_SHORT).show();
                habilitarBotonesColores();
            }
        }.start();
    }

    private void abrirActividadConfiguracion() {
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }

    private void configurarJuego() {
        // Configurar el juego inicialmente
        // ...
    }

    private int obtenerPuntuacionMaxima() {
        // Obtener la puntuación máxima guardada
        // ...
        return 0;
    }



}
