package ru.tasks.logical.task.generator.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Ответ с идентификатором сгенерированного задания")
public class GenerateTaskResponse {

	@Schema(description = "Идентификатор сгенерированного задания")
	private Long taskId;
}
