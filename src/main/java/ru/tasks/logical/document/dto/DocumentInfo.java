package ru.tasks.logical.document.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Информация о документе")
@AllArgsConstructor
@NoArgsConstructor
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
