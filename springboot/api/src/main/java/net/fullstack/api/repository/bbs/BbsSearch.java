package net.fullstack.api.repository.bbs;

import net.fullstack.api.domain.BbsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BbsSearch {
    Page<BbsEntity> search1(Pageable pageable);
    Page<BbsEntity> search2(Pageable pageable, String[] categories, String search_word);
}