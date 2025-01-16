package ru.tasks.logical.gpt.connection;

import ru.tasks.logical.gpt.dto.common.terms.GPTGetTermsFromTextRequest;
import ru.tasks.logical.gpt.dto.common.terms.GPTGetTermsFromTextResponse;

public class MockGPTConnection implements GPTConnection{
	String[] themes = new String[] {
			"алиса", "смартфон", "пайтон", "бот", "программирование", "квадрокоптер", "нейросесть",
			"компьютер", "маркировка"
	};

	String[] questions = new String[] {
			"Сервис для поиска информации в интернете",
			"Портативное устройство массового использования, выполняющие многозадачные функции",
			"Программа нужная для создания приложений",
			"Самый сложный противник в шахматах",
			"Профессия людей, любящих сидеть за компьютером",
			"Летающая тарелка для детей",
			"То чего бояться все программисты",
			"Игра невозможна без…",
			"процесс присвоения метки, которая обеспечивает правильный контекст для каждого ввода в наборе обучающих " +
					"данных или «ответ», который вы хотите, чтобы модель ИИ возвращала во время обучения. " +
					"В компьютерном зрении есть два типа маркировки: аннотация и тегирование. " +
					"Маркировка может выполняться собственными силами или с помощью аутсорсинговых " +
					"или краудсорсинговых услуг"
	};

	@Override
	public GPTGetTermsFromTextResponse parseText(GPTGetTermsFromTextRequest request) {
		return new GPTGetTermsFromTextResponse(themes);
	}
}
