package com.example;

import java.util.ArrayList;
import java.util.List;

public class Jantar {
    private static final int NUM_FILOSOFOS = 5;

    List<Garfo> garfos;
    List<Filosofo> filosofos;

    public void iniciar() {
        garfos = new ArrayList<>();
        filosofos = new ArrayList<>();

        // Cria garfos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            garfos.add(new Garfo(i));
        }

        // Cria filósofos e associa garfos compartilhados
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            Garfo esquerdo = garfos.get(i);
            Garfo direito = garfos.get((i + 1) % NUM_FILOSOFOS);
            Filosofo f = new Filosofo(i, esquerdo, direito);
            filosofos.add(f);
        }

        // (Opcional) Executa cada filósofo em uma thread separada
        for (Filosofo f : filosofos) {
            new Thread(f).start();
        }
    }
}