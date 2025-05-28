package net.fullstack.api.repository.bbs;

import net.fullstack.api.domain.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<BoardEntity> search1(Pageable pageable);
    Page<BoardEntity> search2(Pageable pageable, String[] categories, String search_word);
}