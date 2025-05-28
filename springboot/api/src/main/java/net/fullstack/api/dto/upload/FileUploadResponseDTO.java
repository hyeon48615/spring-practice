package net.fullstack.api.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class FileUploadResponseDTO {
    private String uuid;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private boolean imageFlag;
    private String newFileName;
    private String thumbFileName;
    private String thumbFileType;
    private String thumbFileSize;

    public String getNewFileNameWithUUID() {
        if (imageFlag) {
            return "s_" + uuid + "_" + fileName;
        } else {
            return uuid + "_" + fileName;
        }
    }
}
