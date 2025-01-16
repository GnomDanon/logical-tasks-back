package ru.tasks.logical.task.generator.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Запрос на генерацию задания")
public class GenerateTaskRequest {

	@Schema(name = "Идентификатор генерируемого задания")
	private Long taskId;
}
