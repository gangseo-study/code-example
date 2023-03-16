package com.example.aop.controller;

import com.example.aop.service.AdviceCalcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("calc/advice/v1")
public class AdviceCalcController {
    private final AdviceCalcService adviceCalcService;

    @GetMapping("/factorial/{number}")
    public ResponseEntity<?> calcFactorial(@PathVariable Long number) {
        return new ResponseEntity<>(adviceCalcService.calcFactorial(number).toString(), HttpStatus.OK);
    }

    @GetMapping("/pi/{decimal}")
    public ResponseEntity<?> getPIValue(@PathVariable Long decimal) {
        return new ResponseEntity<>(adviceCalcService.getPIValue(decimal).toString(), HttpStatus.OK);
    }
}