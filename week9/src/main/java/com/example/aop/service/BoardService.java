package com.example.aop.service;


import com.example.aop.mapper.BoardMapper;
import com.example.aop.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper boardMapper;

    public List<Board> findAll() {
        return boardMapper.findAll();
    }


    /**
     * - @Transactional 어노테이션은 기본적으로는 CheckedException에 대해서 rollback 하지 않는다.
     * rollback을 던질 예외 클래스를 지정하여 CheckedException에 대해 rollback할 수 있다.
     */
//    @Transactional(rollbackFor = Exception.class)
    @Transactional
    public void insertWithCheckedException() throws Exception {
        Board board = new Board();
        board.setTitle("TEST1");
        board.setUserId("sanghyeon");

        boardMapper.insertBoard(board);
        throw new Exception("checked");
    }

    /**
     * Unchekced Exception이 발생하면 rollback
     */

    @Transactional
    public void insertWithRuntimeException() {
        Board board = new Board();
        board.setTitle("TEST1");
        board.setUserId("sanghyeon");

        try {
            boardMapper.insertBoard(board);
            throw new RuntimeException("runtime");
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
