package ru.tasks.logical.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tasks.logical.common.dto.TestItem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestQuestions {
	private TestItem[] questions;
}
