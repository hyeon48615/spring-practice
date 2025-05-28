package net.fullstack.api.service;

import net.fullstack.api.dto.BoardDTO;
import net.fullstack.api.dto.PageRequestDTO;
import net.fullstack.api.dto.PageResponseDTO;

import java.util.List;

public interface BoardService {
    public long getTotalCount();
    public List<BoardDTO> bbsList();
    public List<BoardDTO> bbsList(PageRequestDTO requestDTO);
    public BoardDTO getView(long idx);
    public long bbsRegist(BoardDTO dto);
    public long bbsModify(BoardDTO dto);
    public void bbsDelete(long idx);
    public PageResponseDTO<BoardDTO> boardList(PageRequestDTO requestDTO);
}