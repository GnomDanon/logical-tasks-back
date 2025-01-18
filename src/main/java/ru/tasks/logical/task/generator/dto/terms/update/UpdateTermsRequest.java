package ru.tasks.logical.task.generator.dto.terms.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Запрос на изменения терминов")
public class UpdateTermsRequest {

	@Schema(description = "Идентификатор генерируемой задачи")
	private UUID taskId;

	@Schema(description = "Термины")
	private String[] terms;
}
