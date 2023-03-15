package com.example.aop.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calc")
public class Controller {

    @GetMapping("/factorial/{number}")
    public ResponseEntity<?> calcFactorial(@PathVariable String number) {

        return null;
    }

}
