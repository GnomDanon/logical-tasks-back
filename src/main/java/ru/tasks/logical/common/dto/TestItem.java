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
	int correctAnswerIndex;
}
