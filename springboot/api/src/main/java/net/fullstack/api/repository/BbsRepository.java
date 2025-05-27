package net.fullstack.api.repository;

import net.fullstack.api.domain.BbsEntity;
import net.fullstack.api.repository.bbs.BbsSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BbsRepository extends JpaRepository<BbsEntity, Long>, BbsSearch {
    @Query(value = "SELECT NOW()", nativeQuery = true)
    public String getNow();

    @Query(value="SELECT COUNT(*) FROM tbl_board", nativeQuery = true)
    public long getTotalCount();

    Page<BbsEntity> search1(Pageable pageable);
}