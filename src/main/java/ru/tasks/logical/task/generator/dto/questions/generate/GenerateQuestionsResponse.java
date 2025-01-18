package ru.tasks.logical.task.generator.dto.questions.generate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.tasks.logical.common.dto.QuestionAndAnswer;

import java.util.UUID;

@Data
@Schema(description = "Ответ со сгенерированными вопросами")
public class GenerateQuestionsResponse {

	@Schema(description = "Идентификатор генерируемой задачи")
	private UUID taskId;

	@Schema(description = "Вопросы и ответы")
	private QuestionAndAnswer[] questionsAndAnswers;
}
