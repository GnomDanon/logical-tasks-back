package ru.tasks.logical.gpt.connection;

import ru.tasks.logical.gpt.dto.common.terms.GPTGetTermsFromTextRequest;
import ru.tasks.logical.gpt.dto.common.terms.GPTGetTermsFromTextResponse;

public interface GPTConnection {
	GPTGetTermsFromTextResponse parseText(GPTGetTermsFromTextRequest request);
}
