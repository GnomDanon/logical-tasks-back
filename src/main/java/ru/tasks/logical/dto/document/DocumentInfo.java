package ru.tasks.logical.dto.document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.tasks.logical.dto.user.UserInfo;

@Data
@Schema(description = "Информация о документе")
public class DocumentInfo {

    @Schema(description = "Идентификатор документа")
    private Long id;

    @Schema(description = "Название документа")
    private String name;

    @Schema(description = "Описание документа")
    private String description;

    @Schema(description = "Владелец документа")
    private UserInfo owner;
}
