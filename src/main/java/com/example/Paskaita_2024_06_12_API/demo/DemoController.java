package com.example.Paskaita_2024_06_12_API.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7713086";
    private final String USERNAME = "sql7713086";
    private final String PASSWORD = "uEanXdKCDT";
    MSQ_UI  msq_ui = new MSQ_UI(URL,USERNAME,PASSWORD);



//    @GetMapping("/getKlientasID")
//    public int getKleintasID(String name, String password){
//        return msq_ui.getKlientoId(name,password);
//    }

    @PostMapping("/authorizeKlientas")
    public int authorizeKlientas(@RequestBody Klientas klientas) {
        return msq_ui.authorizeKlientas(klientas);
    }

    @PostMapping("/createKlientas")
    public String createKlientas(@RequestBody Klientas klientas){
        return msq_ui.createKlientas(klientas);
    }

    @PostMapping("/setKlientoInfo")
    public String setKlientoInfo(@RequestBody Klientas klientas){
        return msq_ui.setKlientoInfo(klientas);
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody Klientas klientas){
        return msq_ui.resetPassword(klientas);
    }

}
