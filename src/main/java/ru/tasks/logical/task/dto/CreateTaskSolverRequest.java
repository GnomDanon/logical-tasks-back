package ru.tasks.logical.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Запрос на сохранение набранных баллов")
public class CreateTaskSolverRequest {

	@Schema(description = "Фамилия решающего задачу")
	private String firstName;

	@Schema(description = "Имя решающего задачу")
	private String lastName;

	@Schema(description = "Идентификатор решаемой задачи")
	private UUID taskId;

	@Schema(description = "Количество верных ответов")
	private int score;
}
