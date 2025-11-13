package com.example;

public class Garfo {
    private final int id;

    public Garfo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Garfo " + id;
    }
}