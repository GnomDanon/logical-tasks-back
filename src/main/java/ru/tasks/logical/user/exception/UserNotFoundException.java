package ru.tasks.logical.user.exception;

public class UserNotFoundException extends Exception {
	public UserNotFoundException() {
		super("Пользователь не найден");
	}
}
