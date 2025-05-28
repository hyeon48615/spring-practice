package net.fullstack.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import net.fullstack.api.dto.upload.FileUploadDTO;
import net.fullstack.api.dto.upload.FileUploadResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Log4j2
@RestController
@RequestMapping("/file")
@Tag(name = "file", description = "파일 API")
public class FileUploadController {
    @Value("${net.fullstack.upload.path}")
    private String uploadPath;

    @Operation(summary = "POST 방식으로 첨부파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<FileUploadResponseDTO> upload(FileUploadDTO dto) {
        log.info("fileUploadDTO: {}", dto);
        if (dto.getFiles() != null) {
            List<FileUploadResponseDTO> fileList = new ArrayList<>();
            dto.getFiles().forEach(file -> {
                log.info("orgFileName: {}", file.getOriginalFilename());

                String orgFileName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String newFileName = uuid + "_" + orgFileName;
                Path savePath = Paths.get(uploadPath, newFileName);

                boolean isImage = file.getContentType().startsWith("image");
                String thumbFileName = "";
                try {
                    file.transferTo(savePath.toFile());
                    if (isImage) {
                        thumbFileName = "s_" + uuid + "_" + orgFileName;
                        File thumbFile = new File(uploadPath + thumbFileName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                fileList.add(
                        FileUploadResponseDTO.builder()
                                .uuid(uuid)
                                .fileName(orgFileName)
                                .newFileName(newFileName)
                                .imageFlag(isImage)
                                .thumbFileName(thumbFileName)
                                .build()
                );
            });
            return fileList;
        }
        return null;
    }

    @Operation(summary = "POST 방식으로 첨부파일 조회")
    @PostMapping("/{fileName}")
    public ResponseEntity<Resource> viewFileList(
            @PathVariable("fileName") String fileName
    ) {
        log.info("==================================================");
        log.info("FileUploadController >> viewFileList START");
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resourceName + "\"");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        log.info("FileUploadController >> viewFileList END");
        log.info("==================================================");

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @Operation(summary = "DELETE 방식으로 첨부파일 삭제")
    @DeleteMapping("/{fileName}")
    public Map<String, Boolean> deleteFile(
            @PathVariable("fileName") String fileName
    ) {
        log.info("==================================================");
        log.info("FileUploadController >> deleteFile START");
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        String resourceName = resource.getFilename();

        Map<String, Boolean> result = new HashMap<>();
        boolean fileDeleteFlag = false;
        boolean thumbDeleteFlag = false;

        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());

            fileDeleteFlag = resource.getFile().delete();
            if (contentType.startsWith("image")) {
                File thumbFile = new File(uploadPath + File.separator + "s_" + fileName);

                if (thumbFile.exists()) {
                    thumbDeleteFlag = thumbFile.delete();
                }
            }
        } catch (Exception e) {
            log.error("FileUploadController >> deleteFile ERROR", e.getMessage());
        }
        result.put("result", fileDeleteFlag);
        log.info("FileUploadController >> deleteFile END");
        log.info("==================================================");

        return result;
    }
}
