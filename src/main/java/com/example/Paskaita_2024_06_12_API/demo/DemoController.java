package com.example.Paskaita_2024_06_12_API.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/setKlientas")
    public String setKlientas(@RequestBody Klientas klientas){
        return msq_ui.setKlientas(klientas);
    }

    @PostMapping("/setKleintoInfo")
    public String updateInformation(@RequestBody Klientas klientas){
        return msq_ui.setKlientoInfo(klientas);
    }

}
