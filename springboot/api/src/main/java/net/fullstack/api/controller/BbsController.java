package net.fullstack.api.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack.api.dto.BbsDTO;
import net.fullstack.api.service.BbsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/bbs")
public class BbsController {
    private final BbsService bbsService;

    // 게시판 목록
    @RequestMapping("/list")
    public String bbsList(
            @RequestParam(name="page_no", required = false, defaultValue = "1") int page_no,
            @RequestParam(name="page_size", required = false, defaultValue = "10") int page_size,
            Model model
    ) {
        List<BbsDTO> dtoList = bbsService.bbsList();

        model.addAttribute("page_no", page_no);
        model.addAttribute("page_size", page_size);
        model.addAttribute("dtoList", dtoList);

        return "bbs2/list";
    }
    // 게시판 조회
    @GetMapping("/view")
    public String bbsView(
            @RequestParam(name="idx", required=true, defaultValue="0") long idx,
            @RequestParam(name="page_no", required = false, defaultValue = "1") int page_no,
            @RequestParam(name="page_size", required = false, defaultValue = "10") int page_size,
            Model model
    ) {
        BbsDTO dto = bbsService.getView(idx);
        model.addAttribute("idx", idx);
        model.addAttribute("page_no", page_no);
        model.addAttribute("page_size", page_size);
        model.addAttribute("dto", dto);

        model.addAttribute("link_params", "page_no=" + page_no+"&page_size=" + page_size);
        return "bbs2/view";
    }

    // 게시판 등록
    @GetMapping("/regist")
    public String bbsRegistGET(
            @RequestParam(name="page_no", required = false, defaultValue = "1") int page_no,
            @RequestParam(name="page_size", required = false, defaultValue = "10") int page_size,
            Model model
    ) {
        return "bbs/regist";
    }

    // 게시판 등록
    @Transactional
    @PostMapping("/regist")
    public String bbsRegistPOST(
            @RequestParam(name="user_id", required = true) String user_id,
            @RequestParam(name="display_date", required = true) String display_date,
            @RequestParam(name="title", required = true) String title,
            @RequestParam(name="content", required = true) String content,
            Model model
    ) {
        BbsDTO dto = BbsDTO.builder()
                .user_id(user_id)
                .display_date(display_date)
                .title(title)
                .content(content)
                .build();
        long rtnResult = bbsService.bbsRegist(dto);
        if(rtnResult > 0) {
            return "redirect:/bbs/list";
        } else {
            return "redirect:/bbs/regist";
        }
    }

    // 게시판 수정
    @GetMapping("/modify")
    public String bbsModifyGET(
            @RequestParam(name="idx", required=true, defaultValue="0") long idx,
            @RequestParam(name="page_no", required = false, defaultValue = "1") int page_no,
            @RequestParam(name="page_size", required = false, defaultValue = "10") int page_size,
            Model model
    ) {
        return "bbs/modify";
    }

    // 게시판 등록
    @Transactional
    @PostMapping("/modify")
    public String bbsModifyPOST(
            Model model
    ) {
        return "redirect:bbs/list";
    }

    // 게시판 삭제
    @Transactional
    @RequestMapping("/delete")
    public String bbsDelete(
            @RequestParam(name="idx", required=true, defaultValue="0") long idx,
            @RequestParam(name="page_no", required = false, defaultValue = "1") int page_no,
            @RequestParam(name="page_size", required = false, defaultValue = "10") int page_size,
            Model model
    ) {
        return "redirect:bbs/list";
    }
}