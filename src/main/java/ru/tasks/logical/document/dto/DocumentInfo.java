package ru.tasks.logical.document.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Schema(description = "Информация о документе")
@AllArgsConstructor
@NoArgsConstructor
public class DocumentInfo {

    @Schema(description = "Идентификатор документа")
    private UUID id;

    @Schema(description = "Владелец документа")
    private UUID userId;

    @Schema(description = "Название документа")
    private String name;

    @Schema(description = "Описание документа")
    private String description;
}
