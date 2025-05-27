package net.fullstack.api.service;

import net.fullstack.api.dto.BbsDTO;
import net.fullstack.api.dto.PageRequestDTO;
import net.fullstack.api.dto.PageResponseDTO;

import java.util.List;

public interface BbsService {
    public long getTotalCount();
    public List<BbsDTO> bbsList();
    public List<BbsDTO> bbsList(PageRequestDTO requestDTO);
    public BbsDTO getView(long idx);
    public long bbsRegist(BbsDTO dto);
    public long bbsModify(BbsDTO dto);
    public void bbsDelete(long idx);
    public PageResponseDTO<BbsDTO> boardList(PageRequestDTO requestDTO);
}