package ru.tasks.logical.task.generator.dto.terms.generate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Ответ с терминами")
public class GenerateTermsResponse {

	@Schema(description = "Идентификатор генерируемой задачи")
	private UUID taskId;

	@Schema(description = "Термины")
	private String[] terms;
}
