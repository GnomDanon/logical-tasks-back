package ru.tasks.logical.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Вопрос и ответ")
public class QuestionAndAnswer {

	@Schema(name = "Идентификатор вопроса")
	private Long questionId;

	@Schema(name = "Вопрос")
	private String question;

	@Schema(name = "Ответ")
	private String answer;
}
