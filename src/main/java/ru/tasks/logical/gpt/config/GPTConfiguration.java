package ru.tasks.logical.gpt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tasks.logical.gpt.connection.GPTConnection;
import ru.tasks.logical.gpt.connection.GPTConnectionService;
import ru.tasks.logical.gpt.connection.MockGPTConnection;

@Configuration
public class GPTConfiguration {

	@Bean
	public GPTConnection gptConnection() {
		return new GPTConnectionService();
	}
}
