package net.fullstack.api.controller.api.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack.api.dto.BbsReplyDTO;
import net.fullstack.api.dto.PageRequestDTO;
import net.fullstack.api.dto.PageResponseDTO;
import net.fullstack.api.service.BbsReplyService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bbs/replies")
public class BbsReplyController {
    private final BbsReplyService bbsReplyService;

    @Tag(name="Bbs Reply Regist", description = "POST 방식 댓글 등록")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> regist(
            @Valid @RequestBody BbsReplyDTO bbsReplyDTO,
            BindingResult bindingResult
    ) throws BindException {
        if (bindingResult.hasErrors()) {
          throw new BindException(bindingResult);
        }
        Map<String, Long> map = new HashMap<>();
        long rtnResult = bbsReplyService.bbsReplyRegist(bbsReplyDTO);
        map.put("idx", rtnResult);

        return map;
    }

//    @Tag(name="Bbs Reply List", description = "GET 방식의 특정 게시물의 댓글 목록")
//    @GetMapping(value="/list/{bbsidx}")
//    public List<BbsReplyDTO> bbsReplyList(
//        @PathVariable("bbsidx") Long bbs_idx
//    ) {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
//        List<BbsReplyDTO> replyDTOList = bbsReplyService.bbsReplyList(bbs_idx, pageable);
//
//        return replyDTOList;
//    }

    @Tag(name="Bbs Reply List", description = "GET 방식의 특정 게시물의 댓글 목록")
    @GetMapping(value="/list/{bbsidx}")
    public PageResponseDTO<BbsReplyDTO> bbsReplyList(
            @PathVariable("bbsidx") Long bbs_idx,
            @RequestParam(name="page_no", defaultValue="1", required=false) int page_no,
            @RequestParam(name="size_size", defaultValue="10", required=false) int page_size
    ) {
        PageRequestDTO reqDTO = PageRequestDTO.builder()
                .page_no(page_no)
                .page_size(page_size)
                .build();
        return bbsReplyService.bbsReplyList(bbs_idx, reqDTO);
    }

    @Tag(name="Bbs Reply View", description = "GET 방식의 특정 댓글 조회")
    @GetMapping("/{idx}")
    public BbsReplyDTO bbsReplyView(
            @PathVariable Long idx
    ) {
        return bbsReplyService.bbsReplyView(idx);
    }

    @Tag(name="Bbs Reply Modify", description = "PUT 방식의 특정 댓글 수정")
    @PutMapping("/{idx}")
    public Map<String, Long> bbsReplyModify(
            @PathVariable(name="idx") Long idx,
            @Valid @RequestBody BbsReplyDTO bbsReplyDTO
    ) {
        Map<String, Long> map = new HashMap<>();
        bbsReplyService.bbsReplyModify(bbsReplyDTO);
        map.put("idx", idx);
        return map;
    }

    @Tag(name="Bbs Reply Delete", description = "DELETE 방식의 특정 댓글 삭제")
    @DeleteMapping("/{idx}")
    public Map<String, Long> bbsReplyDelete(
            @PathVariable(name="idx") Long idx
    ) {
        Map<String, Long> map = new HashMap<>();
        bbsReplyService.bbsReplyDelete(idx);
        map.put("idx", idx);
        return map;
    }
}