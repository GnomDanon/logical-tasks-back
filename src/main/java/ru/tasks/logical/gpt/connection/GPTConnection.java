package ru.tasks.logical.gpt.connection;

import org.springframework.stereotype.Service;
import ru.tasks.logical.gpt.dto.common.terms.GPTGetTermsFromTextRequest;
import ru.tasks.logical.gpt.dto.common.terms.GPTGetTermsFromTextResponse;

@Service
public interface GPTConnection {
	GPTGetTermsFromTextResponse parseText(GPTGetTermsFromTextRequest request);
}
