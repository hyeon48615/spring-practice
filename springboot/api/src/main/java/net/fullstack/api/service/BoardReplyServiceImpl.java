package net.fullstack.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack.api.domain.BoardEntity;
import net.fullstack.api.domain.BoardReplyEntity;
import net.fullstack.api.dto.BoardReplyDTO;
import net.fullstack.api.dto.PageRequestDTO;
import net.fullstack.api.dto.PageResponseDTO;
import net.fullstack.api.repository.bbs.BoardReplyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class BoardReplyServiceImpl implements BoardReplyService {
    private final ModelMapper modelMapper;
    private final BoardReplyRepository boardReplyRepository;

    /**
     * @param boardReplyDTO
     * @return
     */
    @Override
    public long bbsReplyRegist(BoardReplyDTO boardReplyDTO) {
        BoardReplyEntity bbsReplyEntity = modelMapper.map(boardReplyDTO, BoardReplyEntity.class);
        bbsReplyEntity.setBoard(BoardEntity.builder().idx(boardReplyDTO.getBoard_idx()).build());
        BoardReplyEntity savedResult = boardReplyRepository.save(bbsReplyEntity);
        long rtnResult = savedResult.getIdx();
        return rtnResult;
    }

    /**
     * @param bbs_idx
     * @param pageable
     * @return
     */
    @Override
    public List<BoardReplyDTO> bbsReplyList(Long bbs_idx, Pageable pageable) {
        Page<BoardReplyEntity> resultEntity = boardReplyRepository.list(bbs_idx, pageable);
        List<BoardReplyDTO> boardReplyDTOList = resultEntity.getContent()
                .stream()
                .map(reply->modelMapper.map(reply, BoardReplyDTO.class))
                .collect(Collectors.toList());

        return boardReplyDTOList;
    }

    @Override
    public PageResponseDTO<BoardReplyDTO> bbsReplyList(Long bbs_idx, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("idx");

        Page<BoardReplyEntity> resultEntity = boardReplyRepository.list(bbs_idx, pageable);
        List<BoardReplyDTO> dtoList = resultEntity.getContent().stream()
                .map(entity -> modelMapper.map(entity, BoardReplyDTO.class))
                .collect(Collectors.toList());
        long totalCount = resultEntity.getTotalElements();

        return PageResponseDTO.<BoardReplyDTO>withAll()
                .reqDTO(pageRequestDTO)
                .totalCount(totalCount)
                .dtoList(dtoList)
                .build();
    }

    /**
     * @param idx
     * @return
     */

    @Override
    public BoardReplyDTO bbsReplyView(Long idx) {
        BoardReplyEntity result = boardReplyRepository.getById(idx);
        //log.info("result: " + result);

        BoardReplyDTO replyDTO = modelMapper.map(result, BoardReplyDTO.class);
        if ( result.getBoard() != null ) {
            //log.info("result.getBoard(): " + result.getBoard());
            replyDTO.setBoard_idx(result.getBoard().getIdx());
        }
        return replyDTO;
    }

    /**
     * @param replyDTO
     */
    @Override
    public void bbsReplyModify(BoardReplyDTO replyDTO) {
        Optional<BoardReplyEntity> replyOptional = boardReplyRepository.findById(replyDTO.getIdx());
        if (replyOptional.isPresent()) {
            BoardReplyEntity replyEntity = replyOptional.get();
            replyEntity.modify(replyDTO.getReply_title(), replyDTO.getReply_content());
            log.info("BbsReplyServiceImpl >> bbsReplyModify >> replyEntity : {}", replyEntity);
            boardReplyRepository.save(replyEntity);
        }
    }

    /**
     * @param idx
     */
    @Override
    public void bbsReplyDelete(Long idx) {
        log.info("BbsReplyServiceImpl >> bbsReplyDelete >> idx : {}", idx);
        boardReplyRepository.deleteById(idx);
    }
}