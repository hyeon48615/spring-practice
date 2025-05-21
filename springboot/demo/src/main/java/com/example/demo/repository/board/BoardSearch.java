package com.example.demo.repository.board;

import com.example.demo.domain.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<BoardEntity> search(Pageable pageable);
    Page<BoardEntity> search2(Pageable pageable, String[] categories, String search_word);
}
