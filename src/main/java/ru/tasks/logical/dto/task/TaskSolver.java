package ru.tasks.logical.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.tasks.logical.dto.user.UserInfo;

@Data
@Schema(description = "Соотношение Задание - Решающий")
public class TaskSolver {

    @Schema(description = "Идентификатор соотношения Задание - Решающий")
    private Long id;

    @Schema(description = "Пользователь, решающий задание")
    private UserInfo solver;

    @Schema(description = "Задание, которое решает пользователь")
    private TaskInfo taskInfo;

    @Schema(description = "Счет, который набрал пользователь")
    private int score;

    @Schema(description = "Решено ли задание")
    private boolean isSolved;
}
