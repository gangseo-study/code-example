package com.example.aop.model;

import lombok.Data;

@Data
public class Board {
    private int id;
    private String userId;
    private String title;
    private String content;
}
