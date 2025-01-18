package ru.tasks.logical.task.generator.dto.questions.generate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Запрос на генерацию вопросов")
public class GenerateQuestionsRequest {

	@Schema(description = "Идентификатор задачи, для которой нужно сгенерировать вопросы")
	private UUID taskId;

	@Schema(description = "Количество генерируемых вопросов")
	private int questionsCount;
}
