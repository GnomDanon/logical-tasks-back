package ru.tasks.logical.task.generator.dto.terms.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Ответ с измененными терминами")
public class UpdateTermsResponse {

	@Schema(name = "Идентификатор генерируемой задачи")
	private Long taskId;

	@Schema(name = "Термины")
	private String[] terms;
}
