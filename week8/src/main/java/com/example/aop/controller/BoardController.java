package com.example.aop.controller;

import com.example.aop.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("board/v1")
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/find/all}")
    public ResponseEntity<?> finAll() {
        return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
    }
}
