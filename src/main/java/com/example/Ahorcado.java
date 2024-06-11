package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    private List<String> palabras;
    private String palabraSecreta;
    private char[] estadoActual;
    private int intentosRestantes;

    public Ahorcado() {
        this.palabras = new ArrayList<>();
        cargarPalabras();
        iniciarJuego();
    }

    private void cargarPalabras() {
        // Lista fija de palabras
        palabras.add("programacion");
        palabras.add("java");
        palabras.add("maven");
        palabras.add("consola");
        palabras.add("computadora");
    }

    private void iniciarJuego() {
        Random random = new Random();
        palabraSecreta = palabras.get(random.nextInt(palabras.size()));
        estadoActual = new char[palabraSecreta.length()];
        for (int i = 0; i < estadoActual.length; i++) {
            estadoActual[i] = '_';
        }
        intentosRestantes = 6;
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);

        while (intentosRestantes > 0 && !juegoGanado()) {
            System.out.println("Palabra: " + String.valueOf(estadoActual));
            System.out.println("Intentos restantes: " + intentosRestantes);
            System.out.print("Ingresa una letra: ");
            char letra = scanner.nextLine().charAt(0);

            if (!adivinarLetra(letra)) {
                intentosRestantes--;
            }

            if (juegoGanado()) {
                System.out.println("¡Felicidades! Has adivinado la palabra: " + palabraSecreta);
                break;
            }

            if (intentosRestantes == 0) {
                System.out.println("Te has quedado sin intentos. La palabra era: " + palabraSecreta);
            }
        }

        scanner.close();
    }

    private boolean adivinarLetra(char letra) {
        boolean acierto = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                estadoActual[i] = letra;
                acierto = true;
            }
        }
        return acierto;
    }

    private boolean juegoGanado() {
        for (char c : estadoActual) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
}
