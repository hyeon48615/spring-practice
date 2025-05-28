package net.fullstack.api.repository;

import net.fullstack.api.domain.BoardEntity;
import net.fullstack.api.repository.bbs.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>, BoardSearch {
    @Query(value = "SELECT NOW()", nativeQuery = true)
    public String getNow();

    @Query(value="SELECT COUNT(*) FROM tbl_board", nativeQuery = true)
    public long getTotalCount();

    Page<BoardEntity> search1(Pageable pageable);
}