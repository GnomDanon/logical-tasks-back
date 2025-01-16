package ru.tasks.logical.task.generator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tasks.logical.task.generator.dto.questions.generate.GenerateQuestionsRequest;
import ru.tasks.logical.task.generator.dto.questions.generate.GenerateQuestionsResponse;
import ru.tasks.logical.task.generator.dto.questions.update.UpdateQuestionsRequest;
import ru.tasks.logical.task.generator.dto.questions.update.UpdateQuestionsResponse;
import ru.tasks.logical.task.generator.dto.task.GenerateTaskRequest;
import ru.tasks.logical.task.generator.dto.task.GenerateTaskResponse;
import ru.tasks.logical.task.generator.dto.terms.generate.GenerateTermsRequest;
import ru.tasks.logical.task.generator.dto.terms.generate.GenerateTermsResponse;
import ru.tasks.logical.task.generator.dto.terms.update.UpdateTermsRequest;
import ru.tasks.logical.task.generator.dto.terms.update.UpdateTermsResponse;

@RestController
@RequestMapping("/task/generator")
@RequiredArgsConstructor
@Tag(name = "Генератор задач")
public class TaskGeneratorController {

	@Operation(summary = "Генерация терминов")
	@PostMapping("/terms/generate")
	public GenerateTermsResponse generateTerms(@RequestBody GenerateTermsRequest request) {
		return new GenerateTermsResponse();
	}

	@Operation(summary = "Обновление терминов")
	@PutMapping("/terms/update")
	public UpdateTermsResponse updateTerms(@RequestBody UpdateTermsRequest request) {
		return new UpdateTermsResponse();
	}

	@Operation(summary = "Генерация вопросов")
	@PostMapping("/questions/generate")
	public GenerateQuestionsResponse generateAnswers(@RequestBody GenerateQuestionsRequest request) {
		return new GenerateQuestionsResponse();
	}

	@Operation(summary = "Обновление вопросов")
	@PutMapping("/questions/update")
	public UpdateQuestionsResponse updateAnswers(@RequestBody UpdateQuestionsRequest request) {
		return new UpdateQuestionsResponse();
	}

	@Operation(summary = "Генерация задания")
	@PostMapping("task/generate")
	public GenerateTaskResponse generateTask(@RequestBody GenerateTaskRequest request) {
		return new GenerateTaskResponse();
	}
}
