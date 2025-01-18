package ru.tasks.logical.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tasks.logical.common.dto.CrosswordQuestionItem;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrosswordQuestions {
	private CrosswordQuestionItem[] questions;
}
