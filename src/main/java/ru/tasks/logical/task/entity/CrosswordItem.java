package ru.tasks.logical.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrosswordItem {
	private String clue;
	private String answer;
	private int row;
	private int col;
}
