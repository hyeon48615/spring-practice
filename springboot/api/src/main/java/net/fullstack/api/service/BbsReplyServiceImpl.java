package net.fullstack.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack.api.domain.BbsEntity;
import net.fullstack.api.domain.BbsReplyEntity;
import net.fullstack.api.dto.BbsReplyDTO;
import net.fullstack.api.dto.PageRequestDTO;
import net.fullstack.api.dto.PageResponseDTO;
import net.fullstack.api.repository.bbs.BbsReplyRepository;
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
public class BbsReplyServiceImpl implements BbsReplyService {
    private final ModelMapper modelMapper;
    private final BbsReplyRepository bbsReplyRepository;

    /**
     * @param bbsReplyDTO
     * @return
     */
    @Override
    public long bbsReplyRegist(BbsReplyDTO bbsReplyDTO) {
        BbsReplyEntity bbsReplyEntity = modelMapper.map(bbsReplyDTO, BbsReplyEntity.class);
        bbsReplyEntity.setBoard(BbsEntity.builder().idx(bbsReplyDTO.getBoard_idx()).build());
        BbsReplyEntity savedResult = bbsReplyRepository.save(bbsReplyEntity);
        long rtnResult = savedResult.getIdx();
        return rtnResult;
    }

    /**
     * @param bbs_idx
     * @param pageable
     * @return
     */
    @Override
    public List<BbsReplyDTO> bbsReplyList(Long bbs_idx, Pageable pageable) {
        Page<BbsReplyEntity> resultEntity = bbsReplyRepository.list(bbs_idx, pageable);
        List<BbsReplyDTO> bbsReplyDTOList = resultEntity.getContent()
                .stream()
                .map(reply->modelMapper.map(reply, BbsReplyDTO.class))
                .collect(Collectors.toList());

        return bbsReplyDTOList;
    }

    @Override
    public PageResponseDTO<BbsReplyDTO> bbsReplyList(Long bbs_idx, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("idx");

        Page<BbsReplyEntity> resultEntity = bbsReplyRepository.list(bbs_idx, pageable);
        List<BbsReplyDTO> dtoList = resultEntity.getContent().stream()
                .map(entity -> modelMapper.map(entity, BbsReplyDTO.class))
                .collect(Collectors.toList());
        long totalCount = resultEntity.getTotalElements();

        return PageResponseDTO.<BbsReplyDTO>withAll()
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
    public BbsReplyDTO bbsReplyView(Long idx) {
        BbsReplyEntity result = bbsReplyRepository.getById(idx);
        //log.info("result: " + result);

        BbsReplyDTO replyDTO = modelMapper.map(result, BbsReplyDTO.class);
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
    public void bbsReplyModify(BbsReplyDTO replyDTO) {
        Optional<BbsReplyEntity> replyOptional = bbsReplyRepository.findById(replyDTO.getIdx());
        if (replyOptional.isPresent()) {
            BbsReplyEntity replyEntity = replyOptional.get();
            replyEntity.modify(replyDTO.getReply_title(), replyDTO.getReply_content());
            log.info("BbsReplyServiceImpl >> bbsReplyModify >> replyEntity : {}", replyEntity);
            bbsReplyRepository.save(replyEntity);
        }
    }

    /**
     * @param idx
     */
    @Override
    public void bbsReplyDelete(Long idx) {
        log.info("BbsReplyServiceImpl >> bbsReplyDelete >> idx : {}", idx);
        bbsReplyRepository.deleteById(idx);
    }
}