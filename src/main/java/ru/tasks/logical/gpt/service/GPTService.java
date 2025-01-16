package ru.tasks.logical.gpt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasks.logical.gpt.connection.GPTConnection;

@Service
@RequiredArgsConstructor
public class GPTService {

	private final GPTConnection gptConnection;
}
