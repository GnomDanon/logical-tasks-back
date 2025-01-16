package ru.tasks.logical.gpt.dto.common.questions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class GPTGetQuestionsRequest {
	private MultipartFile text;
	private String[] terms;
	private int questionsCount;
}
