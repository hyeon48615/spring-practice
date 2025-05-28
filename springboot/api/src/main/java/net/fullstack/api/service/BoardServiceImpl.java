package net.fullstack.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack.api.domain.BoardEntity;
import net.fullstack.api.dto.BoardDTO;
import net.fullstack.api.dto.PageRequestDTO;
import net.fullstack.api.dto.PageResponseDTO;
import net.fullstack.api.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    /**
     * @return
     */
    @Override
    public long getTotalCount() {
        return boardRepository.getTotalCount();
    }

    /**
     * @return
     */
    @Override
    public List<BoardDTO> bbsList() {
        // 조건에 따라서 DB 조회
        List<BoardEntity> entityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = entityList.stream()
                .map(entity -> modelMapper.map(entity, BoardDTO.class))
                .collect(Collectors.toList());
        return boardDTOList;
    }

    /**
     * @return
     */
    @Override
    public List<BoardDTO> bbsList(PageRequestDTO pageRequestDTO) {
        // 조건에 따라서 DB 조회
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage_no(),
                pageRequestDTO.getPage_size(),
                Sort.by("idx").descending());
        Page<BoardEntity> result = boardRepository.findAll(pageable);
        List<BoardDTO> boardDTOList = result.getContent().stream()
                .map(entity -> modelMapper.map(entity, BoardDTO.class))
                .collect(Collectors.toList());
        return boardDTOList;
    }

    /**
     * @param idx
     * @return
     */
    @Override
    public BoardDTO getView(long idx) {
        Optional<BoardEntity> bbs = boardRepository.findById(idx);
        return (bbs.isPresent() ? modelMapper.map(bbs.get(), BoardDTO.class) : null);
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public long bbsRegist(BoardDTO dto) {
        BoardEntity entity = modelMapper.map(dto, BoardEntity.class);
        BoardEntity result = boardRepository.save(entity);
        return (result != null) ? result.getIdx() : 0;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public long bbsModify(BoardDTO dto) {
        BoardEntity entity = modelMapper.map(dto, BoardEntity.class);
        entity.setUpdated_at(LocalDateTime.now());
        BoardEntity result = boardRepository.save(entity);
        return (result != null) ? result.getIdx() : 0;
    }

    /**
     * @param idx
     * @return
     */
    @Override
    public void bbsDelete(long idx) {
        boardRepository.deleteById(idx);
    }

    /**
     * @param reqDTO
     * @return
     */
    @Override
    public PageResponseDTO<BoardDTO> boardList(PageRequestDTO reqDTO) {
        //검색 조건에 따른 페이지 객체
        Pageable pageable = PageRequest.of(reqDTO.getPage_no()-1, reqDTO.getPage_size(), Sort.by("idx").descending());
        //Pageable pageable = reqDTO.getPageable(String.valueOf(reqDTO.getPage_no()),String.valueOf(reqDTO.getPage_size()), "idx");
        Page<BoardEntity> result = boardRepository.search2(pageable, reqDTO.getSearch_categories() , reqDTO.getSearch_word());
        List<BoardEntity> entityList = result.getContent();
        List<BoardDTO> boardDTOList = entityList.stream().map(
                entity->modelMapper.map(entity, BoardDTO.class)
        ).collect(Collectors.toList());
        long totalCount = boardRepository.getTotalCount();

        PageResponseDTO<BoardDTO> pageResponseDTO = PageResponseDTO.<BoardDTO>withAll()
                .reqDTO(reqDTO)
                .dtoList(boardDTOList)
                .totalCount(totalCount)
                .build();

        return pageResponseDTO;
    }
}