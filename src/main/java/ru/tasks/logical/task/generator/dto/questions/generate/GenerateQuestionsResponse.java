package ru.tasks.logical.task.generator.dto.questions.generate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.tasks.logical.common.dto.QuestionAndAnswer;

@Data
@Schema(name = "Ответ со сгенерированными вопросами")
public class GenerateQuestionsResponse {

	@Schema(name = "Идентификатор генерируемой задачи")
	private Long taskId;

	@Schema(name = "Вопросы и ответы")
	private QuestionAndAnswer[] questionsAndAnswers;
}
