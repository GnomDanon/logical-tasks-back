package ru.tasks.logical.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.tasks.logical.document.dto.DocumentInfo;
import ru.tasks.logical.user.dto.UserInfo;
import ru.tasks.logical.task.entity.TaskType;

import java.util.Date;

@Data
@Schema(description = "Информация о задании")
public class TaskInfo {

    @Schema(description = "Идентификатор задания")
    private Long id;

    @Schema(description = "Название задания")
    private String name;

    @Schema(description = "Описание задания")
    private String description;

    @Schema(description = "Автор задания")
    private UserInfo author;

    @Schema(description = "Тип задания")
    private TaskType taskType;

    @Schema(description = "Максимальный счет задания")
    private int maxScore;

    @Schema(description = "Документ, на основе которого сгенерировано задание")
    private DocumentInfo documentInfo;

    @Schema(description = "Дата и время создания задания")
    private Date createdAt;

}
