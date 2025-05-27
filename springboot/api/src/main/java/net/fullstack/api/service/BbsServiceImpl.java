package net.fullstack.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack.api.domain.BbsEntity;
import net.fullstack.api.dto.BbsDTO;
import net.fullstack.api.dto.PageRequestDTO;
import net.fullstack.api.dto.PageResponseDTO;
import net.fullstack.api.repository.BbsRepository;
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
public class BbsServiceImpl implements BbsService {
    private final BbsRepository bbsRepository;
    private final ModelMapper modelMapper;

    /**
     * @return
     */
    @Override
    public long getTotalCount() {
        return bbsRepository.getTotalCount();
    }

    /**
     * @return
     */
    @Override
    public List<BbsDTO> bbsList() {
        // 조건에 따라서 DB 조회
        List<BbsEntity> entityList = bbsRepository.findAll();
        List<BbsDTO> bbsDTOList = entityList.stream()
                .map(entity -> modelMapper.map(entity, BbsDTO.class))
                .collect(Collectors.toList());
        return bbsDTOList;
    }

    /**
     * @return
     */
    @Override
    public List<BbsDTO> bbsList(PageRequestDTO pageRequestDTO) {
        // 조건에 따라서 DB 조회
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage_no(),
                pageRequestDTO.getPage_size(),
                Sort.by("idx").descending());
        Page<BbsEntity> result = bbsRepository.findAll(pageable);
        List<BbsDTO> bbsDTOList = result.getContent().stream()
                .map(entity -> modelMapper.map(entity, BbsDTO.class))
                .collect(Collectors.toList());
        return bbsDTOList;
    }

    /**
     * @param idx
     * @return
     */
    @Override
    public BbsDTO getView(long idx) {
        Optional<BbsEntity> bbs = bbsRepository.findById(idx);
        return (bbs.isPresent() ? modelMapper.map(bbs.get(), BbsDTO.class) : null);
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public long bbsRegist(BbsDTO dto) {
        BbsEntity entity = modelMapper.map(dto, BbsEntity.class);
        BbsEntity result = bbsRepository.save(entity);
        return (result != null) ? result.getIdx() : 0;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public long bbsModify(BbsDTO dto) {
        BbsEntity entity = modelMapper.map(dto, BbsEntity.class);
        entity.setUpdated_at(LocalDateTime.now());
        BbsEntity result = bbsRepository.save(entity);
        return (result != null) ? result.getIdx() : 0;
    }

    /**
     * @param idx
     * @return
     */
    @Override
    public void bbsDelete(long idx) {
        bbsRepository.deleteById(idx);
    }

    /**
     * @param reqDTO
     * @return
     */
    @Override
    public PageResponseDTO<BbsDTO> boardList(PageRequestDTO reqDTO) {
        //검색 조건에 따른 페이지 객체
        Pageable pageable = PageRequest.of(reqDTO.getPage_no()-1, reqDTO.getPage_size(), Sort.by("idx").descending());
        //Pageable pageable = reqDTO.getPageable(String.valueOf(reqDTO.getPage_no()),String.valueOf(reqDTO.getPage_size()), "idx");
        Page<BbsEntity> result = bbsRepository.search2(pageable, reqDTO.getSearch_categories() , reqDTO.getSearch_word());
        List<BbsEntity> entityList = result.getContent();
        List<BbsDTO> bbsDTOList = entityList.stream().map(
                entity->modelMapper.map(entity, BbsDTO.class)
        ).collect(Collectors.toList());
        long totalCount = bbsRepository.getTotalCount();

        PageResponseDTO<BbsDTO> pageResponseDTO = PageResponseDTO.<BbsDTO>withAll()
                .reqDTO(reqDTO)
                .dtoList(bbsDTOList)
                .totalCount(totalCount)
                .build();

        return pageResponseDTO;
    }
}