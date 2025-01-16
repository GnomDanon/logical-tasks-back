package ru.tasks.logical.task.generator.dto.terms.generate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Ответ с терминами")
public class GenerateTermsResponse {

	@Schema(name = "Идентификатор генерируемой задачи")
	private Long taskId;

	@Schema(name = "Термины")
	private String[] terms;
}
