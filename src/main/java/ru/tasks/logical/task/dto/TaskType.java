package ru.tasks.logical.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Тип задания")
public class TaskType {

    @Schema(description = "Название типа задания")
    private String taskTypeName;

    @Schema(description = "URI изображения задания")
    private String imageUri;
}
