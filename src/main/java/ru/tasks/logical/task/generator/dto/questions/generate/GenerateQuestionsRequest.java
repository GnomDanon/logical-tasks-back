package ru.tasks.logical.task.generator.dto.questions.generate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Запрос на генерацию вопросов")
public class GenerateQuestionsRequest {

	@Schema(name = "Идентификатор задачи, для которой нужно сгенерировать вопросы")
	private Long taskId;

	@Schema(name = "Количество генерируемых вопросов")
	private int questionsCount;
}
