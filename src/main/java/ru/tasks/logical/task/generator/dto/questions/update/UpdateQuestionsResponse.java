package ru.tasks.logical.task.generator.dto.questions.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.tasks.logical.task.generator.dto.questions.Question;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Schema(description = "Ответ с измененными вопросами и ответами")
public class UpdateQuestionsResponse {

	@Schema(description = "Идентификатор генерируемой задачи")
	private UUID taskId;

	@Schema(description = "Вопросы и ответы")
	private Question[] questions;
}
