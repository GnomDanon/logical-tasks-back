package ru.tasks.logical.task.generator.dto.questions.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.tasks.logical.common.dto.QuestionAndAnswer;

@Data
@Schema(description = "Запрос на изменение вопросов и ответов")
public class UpdateQuestionsRequest {

	@Schema(description = "Идентификатор генерируемой задачи")
	private Long taskId;

	@Schema(description = "Вопросы и ответы")
	private QuestionAndAnswer[] questionsAndAnswers;
}
