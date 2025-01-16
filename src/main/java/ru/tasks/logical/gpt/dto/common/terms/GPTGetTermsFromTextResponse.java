package ru.tasks.logical.gpt.dto.common.terms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GPTGetTermsFromTextResponse {
	private String[] terms;
}
