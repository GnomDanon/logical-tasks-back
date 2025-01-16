package ru.tasks.logical.task.generator.dto.terms.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Ответ с измененными терминами")
public class UpdateTermsResponse {

	@Schema(description = "Идентификатор генерируемой задачи")
	private Long taskId;

	@Schema(description = "Термины")
	private String[] terms;
}
