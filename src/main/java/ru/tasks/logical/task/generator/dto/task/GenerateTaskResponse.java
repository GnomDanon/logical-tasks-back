package ru.tasks.logical.task.generator.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Ответ с идентификатором сгенерированного задания")
public class GenerateTaskResponse {

	@Schema(description = "Идентификатор сгенерированного задания")
	private UUID taskId;
}
