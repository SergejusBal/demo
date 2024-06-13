package com.example.Paskaita_2024_06_12_API.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7713086";
    private final String USERNAME = "sql7713086";
    private final String PASSWORD = "uEanXdKCDT";
    MSQ_UI  msq_ui = new MSQ_UI(URL,USERNAME,PASSWORD);



    @GetMapping("/getKlientasID")
    public int getKleintasID(String name, String password){
        return msq_ui.getKlientoId(name,password);
    }
    @GetMapping("/setKlientas")
    public String setKlientas(String name, String password){
        return msq_ui.setKlientas(name,password);
    }

    @GetMapping("/setKleintoInfo")
    public String updateInformation(int id, String name, String surname, String tel_number, String city){
        Klientas klientas = new Klientas();
        klientas.setId(id);
        klientas.setName(name);
        klientas.setSurname(surname);
        klientas.setTelefonoNumeris(tel_number);
        klientas.setMiestas(city);

        return msq_ui.setKlientoInfo(klientas);
    }




}
