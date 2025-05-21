package com.example.demo.repository;

import com.example.demo.domain.BoardEntity;
import com.example.demo.repository.board.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>, BoardSearch {
    @Query(value="SELECT NOW()", nativeQuery=true)
    public String getNow();

    @Query(value="SELECT COUNT(*) FROM tbl_board", nativeQuery=true)
    public long getTotalCount();

    Page<BoardEntity> search(Pageable pageable);
    Page<BoardEntity> search2(Pageable pageable, String[] categories, String search_word);
}
