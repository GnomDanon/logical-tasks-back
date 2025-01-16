package ru.tasks.logical.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Вопрос и ответ")
public class QuestionAndAnswer {

	@Schema(description = "Идентификатор вопроса")
	private Long questionId;

	@Schema(description = "Вопрос")
	private String question;

	@Schema(description = "Ответ")
	private String answer;
}
