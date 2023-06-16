package com.example.ejercicio101112security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

@GetMapping("/saludo")
    public String saludo(){
        return "hola al universo entero";
    }
}
