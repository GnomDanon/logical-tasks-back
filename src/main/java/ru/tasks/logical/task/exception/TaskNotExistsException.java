package ru.tasks.logical.task.exception;

public class TaskNotExistsException extends Exception {
	public TaskNotExistsException() {
		super("Задача не найдена");
	}
}
