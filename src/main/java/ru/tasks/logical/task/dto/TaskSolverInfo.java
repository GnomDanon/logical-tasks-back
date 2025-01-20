package ru.tasks.logical.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Соотношение Задание - Решающий")
public class TaskSolverInfo {

    @Schema(description = "Идентификатор соотношения Задание - Решающий")
    private UUID id;

    @Schema(description = "Имя решающего задание")
    private String firstName;

    @Schema(description = "Фамилия решающего задание")
    private String lastName;

    @Schema(description = "Идентификатор задания, которое решает пользователь")
    private UUID taskId;

    @Schema(description = "Счет, который набрал пользователь")
    private int score;

    @Schema(description = "Максимально возможный счет")
    private int maxScore;
}
