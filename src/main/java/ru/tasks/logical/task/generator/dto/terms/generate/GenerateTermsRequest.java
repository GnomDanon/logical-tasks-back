package ru.tasks.logical.task.generator.dto.terms.generate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Запрос на генерацию терминов из текста")
public class GenerateTermsRequest {

	@Schema(name = "Идентификатор документа")
	private Long documentId;

	@Schema(name = "Количество генерируемых терминов")
	private int termsCount;
}
