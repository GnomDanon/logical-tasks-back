package ru.tasks.logical.document.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ru.tasks.logical.user.dto.UserInfo;

@Data
@Schema(description = "Запрос на загрузку документа")
public class DocumentUploadRequest {

    @Schema(description = "Название документа")
    private String name;

    @Schema(description = "Описание документа")
    private String description;

    @Schema(description = "Владелец документа")
    private UserInfo owner;

    @Schema(description = "Документ")
    private MultipartFile file;
}
