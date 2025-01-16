package ru.tasks.logical.gpt.dto.common.terms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class GPTGetTermsFromTextRequest {
	private MultipartFile text;
	private int questionsCount;
}
