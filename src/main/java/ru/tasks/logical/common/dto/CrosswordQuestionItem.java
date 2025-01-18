package ru.tasks.logical.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrosswordQuestionItem {
	private String question;
	private String answer;
}
