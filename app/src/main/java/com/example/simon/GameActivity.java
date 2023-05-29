package com.example.simon;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private static final int DEFAULT_SEQUENCE_LENGTH = 4;
    private Button btnColor1;
    private Button btnColor2;
    private Button btnColor3;
    private Button btnColor4;
    private List<Integer> sequence;
    private int currentRound;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnColor1 = findViewById(R.id.btnColor1);
        btnColor2 = findViewById(R.id.btnColor2);
        btnColor3 = findViewById(R.id.btnColor3);
        btnColor4 = findViewById(R.id.btnColor4);

        sequence = generateRandomSequence(DEFAULT_SEQUENCE_LENGTH);
        currentRound = 0;
        score = 0;

        playSequence();
    }

    private void playSequence() {
        // Mostrar cada color de la secuencia durante un instante
        // y reproducir el sonido correspondiente (opcional)

        // Aquí se puede usar una animación, cambiar el fondo del botón o cualquier otra técnica visual
        // para indicar el color

        // Después de mostrar cada color, se puede usar un Handler con un retardo para ocultarlo
        // y pasar al siguiente color en la secuencia
    }

    // Generar una secuencia aleatoria de colores
    private List<Integer> generateRandomSequence(int length) {
        List<Integer> sequence = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int color = random.nextInt(4); // Número de colores disponibles
            sequence.add(color);
        }

        return sequence;
    }

    // Verificar si la secuencia ingresada por el usuario es correcta
    private boolean isCorrectSequence(List<Integer> inputSequence) {
        // Comparar la secuencia ingresada con la secuencia generada
        // y verificar si los colores son los mismos y están en el mismo orden
        // Si la secuencia es correcta, incrementar la puntuación y pasar a la siguiente ronda
        // Si la secuencia es incorrecta, reproducir la última combinación no acertada
        // y pedir al usuario que la intente volver a introducir adecuadamente
    }

    // Mostrar un Toast con un mensaje específico
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Calcular la puntuación extra por cada secuencia acertada
    private int calculateExtraScore(int sequenceLength) {
        double extraPoints = sequenceLength * 0.7;
        return (int) Math.ceil(extraPoints);
    }
}
