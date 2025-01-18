package ru.tasks.logical.task.generator.dto.terms.generate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Запрос на генерацию терминов из текста")
public class GenerateTermsRequest {

	@Schema(description = "Идентификатор пользователя, генерирующего задачу")
	private UUID authorId;

	@Schema(description = "Идентификатор документа")
	private UUID documentId;

	@Schema(description = "Количество генерируемых терминов")
	private int termsCount;

	@Schema(description = "Тип генерируемой задачи")
	private String taskType;
}
