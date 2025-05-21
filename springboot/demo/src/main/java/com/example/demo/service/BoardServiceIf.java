package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;

public interface BoardServiceIf {
    public Long regist(BoardDTO boardDTO);
    public BoardDTO view(Long idx);
    public void modify(BoardDTO boardDTO);
    public void delete(Long idx);
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
    public PageResponseDTO<BoardDTO> search(PageRequestDTO pageRequestDTO);
}
