package ru.tasks.logical.task.generator.dto.terms.generate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Запрос на генерацию терминов из текста")
public class GenerateTermsRequest {

	@Schema(description = "Идентификатор документа")
	private Long documentId;

	@Schema(description = "Количество генерируемых терминов")
	private int termsCount;
}
