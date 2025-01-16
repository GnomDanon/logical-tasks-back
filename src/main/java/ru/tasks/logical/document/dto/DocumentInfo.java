package ru.tasks.logical.document.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Информация о документе")
public class DocumentInfo {

    @Schema(description = "Идентификатор документа")
    private Long id;

    @Schema(description = "Владелец документа")
    private Long userId;

    @Schema(description = "Название документа")
    private String name;

    @Schema(description = "Описание документа")
    private String description;
}
