package ru.tasks.logical.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Элемент теста")
@NoArgsConstructor
@AllArgsConstructor
public class TestItem {
	String question;
	String[] answers;
	String correctAnswer;

	public TestItem(String question, String[] answers, int correctAnswerIndex) {
		this.question = question;
		this.answers = answers;
		this.correctAnswer = Integer.toString(correctAnswerIndex);
	}

	public int getCorrectAnswer() {
		try {
			return Integer.parseInt(correctAnswer);
		} catch (NumberFormatException exception) {
			return switch (correctAnswer) {
				case "B" -> 1;
				case "C" -> 2;
				case "D" -> 3;
				default -> 0;
			};
		}
	}
}
