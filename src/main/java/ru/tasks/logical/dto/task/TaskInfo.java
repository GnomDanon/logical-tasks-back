package ru.tasks.logical.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.tasks.logical.dto.document.DocumentInfo;
import ru.tasks.logical.dto.user.UserInfo;
import ru.tasks.logical.entity.TaskType;

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
