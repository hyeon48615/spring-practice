package net.fullstack.api.service;

import net.fullstack.api.dto.BbsReplyDTO;
import net.fullstack.api.dto.PageRequestDTO;
import net.fullstack.api.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BbsReplyService {
    public long bbsReplyRegist(BbsReplyDTO replyDTO);
    public List<BbsReplyDTO> bbsReplyList(Long bbs_idx, Pageable pageable);
    public PageResponseDTO<BbsReplyDTO> bbsReplyList(Long bbs_idx, PageRequestDTO pageRequestDTO);
    public BbsReplyDTO bbsReplyView(Long idx);
    public void bbsReplyModify(BbsReplyDTO replyDTO);
    public void bbsReplyDelete(Long idx);
}