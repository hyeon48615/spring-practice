package com.example.demo.service;

import com.example.demo.domain.BoardEntity;
import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@RequiredArgsConstructor
@Transactional
public class BoardService implements BoardServiceIf {

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Long regist(BoardDTO boardDTO) {
        BoardEntity boardEntity = modelMapper.map(boardDTO, BoardEntity.class);
        Long idx = boardRepository.save(boardEntity).getIdx();
        return idx;
    }

    @Override
    public BoardDTO view(Long idx) {
        Optional<BoardEntity> result = boardRepository.findById(idx);
        BoardEntity boardEntity = result.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(boardEntity, BoardDTO.class);
        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<BoardEntity> result = boardRepository.findById(boardDTO.getIdx());
        BoardEntity boardEntity = result.orElseThrow();
        boardEntity.modify(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getDisplay_date());
        boardRepository.save(boardEntity);
    }

    @Override
    public void delete(Long idx) {
        boardRepository.deleteById(idx);
    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("idx");

        Page<BoardEntity> result = boardRepository.findAll(pageable);

        List<BoardDTO> list = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());
        long totalCount = result.getTotalElements();

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .list(list)
                .total(totalCount)
                .build();
    }

    @Override
    public PageResponseDTO<BoardDTO> search(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("idx");
        String[] categories = new String[]{pageRequestDTO.getSearch_category()};
        String search_word = pageRequestDTO.getSearch_word();

        Page<BoardEntity> result = boardRepository.search2(pageable, categories, search_word);

        List<BoardEntity> entityList = result.getContent();
        List<BoardDTO> list = entityList.stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());
        long totalCount = result.getTotalElements();

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .list(list)
                .total(totalCount)
                .build();
    }
}
