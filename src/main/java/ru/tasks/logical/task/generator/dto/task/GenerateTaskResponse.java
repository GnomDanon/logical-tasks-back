package ru.tasks.logical.task.generator.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Ответ с идентификатором сгенерированного задания")
public class GenerateTaskResponse {

	@Schema(name = "Идентификатор сгенерированного задания")
	private Long taskId;
}
