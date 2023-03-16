package com.example.aop.mapper;

import com.example.aop.model.Board;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> findAll();
}
