package ru.tasks.logical.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Запрос на открытие доступа к заданию пользователю по электронной почте")
public class CreateTaskSolverRequest {

    @Schema(description = "Идентификатор задания")
    private Long taskId;

    @Schema(description = "Адрес электронной почты пользователя, которому нужно выдать доступ к заданию")
    private String email;
}
