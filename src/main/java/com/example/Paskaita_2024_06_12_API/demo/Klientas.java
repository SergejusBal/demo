package com.example.Paskaita_2024_06_12_API.demo;

public class Klientas {

    private int id;
    private String name;
    private String surname;

    private String username;
    private String password;

    private String telefonoNumeris;
    private String miestas;

    public Klientas() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTelefonoNumeris() {
        return telefonoNumeris;
    }

    public String getMiestas() {
        return miestas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelefonoNumeris(String telefonoNumeris) {
        this.telefonoNumeris = telefonoNumeris;
    }

    public void setMiestas(String miestas) {
        this.miestas = miestas;
    }
}
