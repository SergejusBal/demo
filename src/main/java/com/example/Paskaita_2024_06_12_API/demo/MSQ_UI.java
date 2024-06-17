package com.example.Paskaita_2024_06_12_API.demo;

import java.sql.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MSQ_UI {

    private String URL;
    private String USERNAME;
    private String PASSWORD;

    public MSQ_UI(String URL, String USERNAME, String PASSWORD) {
        this.PASSWORD = PASSWORD;
        this.URL = URL;
        this.USERNAME = USERNAME;
    }


    public String resetPassword(Klientas klientas){
        int userID = checkRegistrationID(klientas.getEmail());
        String userPassword = generatePassword(5);

        setOneParameterFromKlientas(userID,"password",userPassword);

        String gmailMessage = String.format("Hello, \n Your new password is: %s",userPassword);
        GmailMail gmailMail = new GmailMail();
        gmailMail.sendMail(klientas.getEmail(),gmailMessage);

        return "Password was reset.";
    }


    public int authorizeKlientas(Klientas klientas){
        String sql = "SELECT * FROM Klientas WHERE username = ? AND password = ?";
        int klientoId = -1;

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,klientas.getUsername());
            statement.setString(2,klientas.getPassword());
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                klientoId = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return klientoId;
    }

    public String createKlientas(Klientas klientas){

        if(!checkEmailFormat(klientas.getEmail())) return "Invalid email format.";
        if(checkRegistrationID(klientas.getEmail()) != -1) return "E-mail already exists.";

        String userPassword = generatePassword(5);

        String gmailMessage = String.format("Hello, \n Your password is: %s",userPassword);
        GmailMail gmailMail = new GmailMail();
        gmailMail.sendMail(klientas.getEmail(),gmailMessage);

        String sql = "INSERT INTO Klientas (username, password, email) VALUES (?,?,?);";
        String message = "User was successfully added.";

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,klientas.getUsername());
            statement.setString(2,userPassword);
            statement.setString(3,klientas.getEmail());
            statement.executeUpdate();

        } catch (SQLException e) {
            message = e.getMessage();
        } catch (Exception e){
            message = e.getMessage();
        }
        return message;
    }

    private boolean checkEmailFormat(String email){
        //Pattern take from
        String emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    private int checkRegistrationID(String email) {

        String sql = "SELECT * FROM Klientas WHERE email = ?;";
        int klientoId = -1;

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,email);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                klientoId = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return klientoId;
    }

    private String generatePassword(int size){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < size; i++){
            stringBuilder.append((char)random.nextInt(48,126));
        }
        return stringBuilder.toString();
    }

    public String setKlientoInfo(Klientas klientas){
        int userId = klientas.getId();

        if(userId != 0 ){
            if(klientas.getName() != null && !klientas.getName().isEmpty() )
                setOneParameterFromKlientoInfo(userId,"name",klientas.getName());
            if(klientas.getSurname() != null && !klientas.getSurname().isEmpty())
                setOneParameterFromKlientoInfo(userId,"surname",klientas.getSurname());
            if(klientas.getCity() != null  && !klientas.getCity().isEmpty())
                setOneParameterFromKlientoInfo(userId,"city",klientas.getCity());
            if(klientas.getPhoneNumber() != null && !klientas.getPhoneNumber().isEmpty())
                setOneParameterFromKlientoInfo(userId,"phone_number",klientas.getPhoneNumber());
        }

        return "User data was updated";
    }

    private void setOneParameterFromKlientoInfo(int userID, String dataBaseParameter, String value){
        String sql = String.format("INSERT INTO KlientoInfo (id,%s) VALUES (?,?)\n" +
                     "ON DUPLICATE KEY UPDATE %s = ?;",dataBaseParameter,dataBaseParameter);
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,userID);
            statement.setString(2,value);
            statement.setString(3,value);
            statement.executeUpdate();
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    private void setOneParameterFromKlientas(int userID, String dataBaseParameter, String value){
        String sql = String.format("UPDATE Klientas SET %s = ? WHERE id = ?", dataBaseParameter);
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,value);
            statement.setInt(2,userID);
            statement.executeUpdate();
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
