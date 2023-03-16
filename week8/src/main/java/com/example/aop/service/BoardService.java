package com.example.aop.service;


import com.example.aop.mapper.BoardMapper;
import com.example.aop.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper boardMapper;

    public List<Board> findAll() {
        return boardMapper.findAll();
    }
}
