package ru.tasks.logical.task.generator.dto.questions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Универсальная схема вопроса и ответов")
@NoArgsConstructor
@AllArgsConstructor
public class Question {

	@Schema(description = "Вопрос")
	private String question;

	@Schema(description = "Варианты ответов")
	private String[] answers;

	@Schema(description = "Индекс правильного варианта ответа")
	private int correctAnswerIndex;

	public static Question test(String question, String[] answers, int correctAnswerIndex) {
		return new Question(question, answers, correctAnswerIndex);
	}

	public static Question crossword(String question, String answer) {
		return new Question(question, new String[] {answer}, 0);
	}
}
