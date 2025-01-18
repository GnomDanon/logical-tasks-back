package ru.tasks.logical.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.tasks.logical.user.dto.UserInfo;

import java.util.UUID;

@Data
@Schema(description = "Соотношение Задание - Решающий")
public class TaskSolverInfo {

    @Schema(description = "Идентификатор соотношения Задание - Решающий")
    private UUID id;

    @Schema(description = "Пользователь, решающий задание")
    private UserInfo solver;

    @Schema(description = "Задание, которое решает пользователь")
    private TaskInfo taskInfo;

    @Schema(description = "Счет, который набрал пользователь")
    private int score;

    @Schema(description = "Решено ли задание")
    private boolean isSolved;
}
