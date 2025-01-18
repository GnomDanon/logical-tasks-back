package ru.tasks.logical.document.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Запрос на загрузку документа")
public class DocumentUploadRequest {

    @Schema(description = "Владелец документа")
    private UUID userId;

    @Schema(description = "Название документа")
    private String name;

    @Schema(description = "Описание документа")
    private String description;
}
