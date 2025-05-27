package net.fullstack.api.repository.bbs;

import net.fullstack.api.domain.BbsReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BbsReplyRepository extends JpaRepository<BbsReplyEntity, Long> {
    @Query(value = "SELECT r FROM BbsReplyEntity r WHERE r.board.idx = :bbs_idx")
    //@Query(value = "SELECT * FROM tbl_board_reply WHERE board_idx = :bbs_idx", nativeQuery = true)
    Page<BbsReplyEntity> list(@Param("bbs_idx") Long bbs_idx, Pageable pageable);

    //@Query(value = "SELECT * FROM tbl_board_reply WHERE idx = :idx", nativeQuery = true)
    @Query(value = "SELECT r FROM BbsReplyEntity r WHERE r.idx = :idx")
    BbsReplyEntity getById(@Param("idx") Long idx);
}