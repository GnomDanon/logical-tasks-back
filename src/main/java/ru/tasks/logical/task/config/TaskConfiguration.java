package ru.tasks.logical.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tasks.logical.task.generator.service.crossword.CrosswordGenerator;
import ru.tasks.logical.task.generator.service.crossword.CrosswordGeneratorService;

@Configuration
public class TaskConfiguration {

	@Bean
	public CrosswordGenerator crosswordGenerator() {
		return new CrosswordGeneratorService();
	}
}
