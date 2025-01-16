package ru.tasks.logical.gpt.dto.common.questions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GPTGetQuestionsResponse {
	String[] questions;
	String[] answers;
}
