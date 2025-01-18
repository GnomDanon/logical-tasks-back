package ru.tasks.logical.task.generator.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Запрос на генерацию задания")
public class GenerateTaskRequest {

	@Schema(description = "Идентификатор генерируемого задания")
	private UUID taskId;

	@Schema(description = "Название генерируемой задачи")
	private String taskName;
}
