package ru.tasks.logical.task.generator.dto.questions.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.tasks.logical.common.dto.QuestionAndAnswer;

@Data
@Schema(description = "Ответ с измененными вопросами и ответами")
public class UpdateQuestionsResponse {

	@Schema(description = "Идентификатор генерируемой задачи")
	private Long taskId;

	@Schema(description = "Вопросы и ответы")
	private QuestionAndAnswer[] questionsAndAnswers;
}
