package com.example.tp3_bdd;

import java.io.Serializable;

public class Sports implements Serializable {
    private int id;
    private String name;
    private String category;
    private int numberOfPlayers;

    public Sports(int id, String name, String category, int numberOfPlayers) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.numberOfPlayers = numberOfPlayers;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public String toString() {
        return name + " - " + category + " (" + numberOfPlayers + " players)";
    }
}
