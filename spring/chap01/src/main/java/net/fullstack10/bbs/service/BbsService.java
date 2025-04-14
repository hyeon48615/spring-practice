package net.fullstack10.bbs.service;

import net.fullstack10.bbs.dto.BbsDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum BbsService {
    INSTANCE;

    public void regist(BbsDTO dto) {
        System.out.println("====================");
        System.out.println("regist");
        System.out.println("====================");
    }

    public List<BbsDTO> list() {
        List<BbsDTO> dtoList = IntStream.range(0, 10).mapToObj(i -> {
            BbsDTO dto = new BbsDTO();
            dto.setIdx(i);
            dto.setUser_id("user" + i);
            dto.setTitle("제목" + i);
            dto.setContent("내용" + i);
            dto.setReg_date(LocalDateTime.now());
            return dto;
        }).collect(Collectors.toList());

        return dtoList;
    }

    public BbsDTO view(int idx) {
        BbsDTO dto = new BbsDTO();
        dto.setIdx(idx);
        dto.setUser_id("user" + idx);
        dto.setTitle("제목" + idx);
        dto.setContent("내용" + idx);
        dto.setReg_date(LocalDateTime.now());

        return dto;
    }

    public void modify() {

    }

    public void delete() {

    }
}
