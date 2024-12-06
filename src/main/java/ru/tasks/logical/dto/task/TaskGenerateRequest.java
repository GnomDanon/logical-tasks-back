package ru.tasks.logical.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Запрос на генерацию задания")
public class TaskGenerateRequest {

    @Schema(description = "Id документа, на основе которого нужно сгенерировать задание")
    private Long documentId;

    @Schema(description = "Id автора задания")
    private Long userId;

    @Schema(description = "Тип генерируемого задания")
    private String taskType;
}
