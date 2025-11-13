package com.example;

public class Filosofo implements Runnable {
    private final int id;
    Garfo garfoEsquerdo;
    Garfo garfoDireito;

    public Filosofo(int id, Garfo garfoEsquerdo, Garfo garfoDireito) {
        this.id = id;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
    }

    @Override
    public void run() {
        pensar();
        comer();
    }

    private void pensar() {
        System.out.println("Filósofo " + id + " está pensando...");
        try {
            Thread.sleep((int) (Math.random() * 500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void comer() {
        synchronized (garfoEsquerdo) {
            synchronized (garfoDireito) {
                System.out.println("Filósofo " + id + " está comendo com " +
                        garfoEsquerdo + " e " + garfoDireito);
                try {
                    Thread.sleep((int) (Math.random() * 500));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}