package com.example.Paskaita_2024_06_12_API.demo;

import java.sql.*;

public class MSQ_UI {

    private String URL;
    private String USERNAME;
    private String PASSWORD;

    public MSQ_UI(String URL, String USERNAME, String PASSWORD) {
        this.PASSWORD = PASSWORD;
        this.URL = URL;
        this.USERNAME = USERNAME;
    }

    public int getKlientoId(String name, String password) {

        String sql = "SELECT * FROM Klientas WHERE username = ? AND password = ?;";
        int klientoId = -1;

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                klientoId = resultSet.getInt("id");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return klientoId;
    }

    public String setKlientas(String name, String password){

        String sql = "INSERT INTO Klientas (username, password) VALUES (?,?);";
        String message = "user successfully added";

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,name);
            statement.setString(2,password);
            statement.executeUpdate();

        } catch (SQLException e) {
            message = e.getMessage();
        } catch (Exception e){
            message = e.getMessage();
        }

        return message;
    }
    public String setKlientoInfo(Klientas klientas){

        String sql = "INSERT INTO KlientoInfo (id,vardas,pavarde,telefono_numeris,miestas) VALUES (?,?,?,?,?)\n" +
                     "ON DUPLICATE KEY UPDATE vardas = ?, pavarde = ?, telefono_numeris = ?, miestas = ?;";
        String message = "info successfully added";

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,klientas.getId());
            statement.setString(2,klientas.getName());
            statement.setString(3,klientas.getSurname());
            statement.setString(4,klientas.getTelefonoNumeris());
            statement.setString(5,klientas.getMiestas());
            statement.setString(6,klientas.getName());
            statement.setString(7,klientas.getSurname());
            statement.setString(8,klientas.getTelefonoNumeris());
            statement.setString(9,klientas.getMiestas());
            statement.executeUpdate();

        } catch (SQLException e) {
            message = e.getMessage();
        } catch (Exception e){
            message = e.getMessage();
        }

        return message;
    }




}
