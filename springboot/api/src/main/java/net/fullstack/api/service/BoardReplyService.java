package net.fullstack.api.service;

import net.fullstack.api.dto.BoardReplyDTO;
import net.fullstack.api.dto.PageRequestDTO;
import net.fullstack.api.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardReplyService {
    public long bbsReplyRegist(BoardReplyDTO replyDTO);
    public List<BoardReplyDTO> bbsReplyList(Long bbs_idx, Pageable pageable);
    public PageResponseDTO<BoardReplyDTO> bbsReplyList(Long bbs_idx, PageRequestDTO pageRequestDTO);
    public BoardReplyDTO bbsReplyView(Long idx);
    public void bbsReplyModify(BoardReplyDTO replyDTO);
    public void bbsReplyDelete(Long idx);
}