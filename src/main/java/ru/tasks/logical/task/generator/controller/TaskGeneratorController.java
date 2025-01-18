package ru.tasks.logical.task.generator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tasks.logical.document.exception.DocumentNotExistsException;
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
import ru.tasks.logical.task.generator.service.TaskGeneratorService;
import ru.tasks.logical.user.exception.UserNotFoundException;

@RestController
@RequestMapping("/task/generator")
@RequiredArgsConstructor
@Tag(name = "Генератор задач")
public class TaskGeneratorController {

	private final TaskGeneratorService taskGeneratorService;

	@Operation(summary = "Генерация терминов")
	@PostMapping("/terms/generate")
	public ResponseEntity<GenerateTermsResponse> generateTerms(@RequestBody GenerateTermsRequest request) {
		try {
			return ResponseEntity.ok(taskGeneratorService.generateTerms(request));
		} catch (DocumentNotExistsException | UserNotFoundException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@Operation(summary = "Обновление терминов")
	@PutMapping("/terms/update")
	public ResponseEntity<UpdateTermsResponse> updateTerms(@RequestBody UpdateTermsRequest request) {
		try {
			return ResponseEntity.ok(taskGeneratorService.updateTerms(request));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@Operation(summary = "Генерация вопросов")
	@PostMapping("/questions/generate")
	public ResponseEntity<GenerateQuestionsResponse> generateQuestions(@RequestBody GenerateQuestionsRequest request) {
		try {
			return ResponseEntity.ok(taskGeneratorService.generateQuestions(request));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@Operation(summary = "Обновление вопросов")
	@PutMapping("/questions/update")
	public ResponseEntity<UpdateQuestionsResponse> updateQuestions(@RequestBody UpdateQuestionsRequest request) {
		try {
			return ResponseEntity.ok(taskGeneratorService.updateQuestions(request));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@Operation(summary = "Генерация задания")
	@PostMapping("task/generate")
	public ResponseEntity<GenerateTaskResponse> generateTask(@RequestBody GenerateTaskRequest request) {
		try {
			return ResponseEntity.ok(taskGeneratorService.generateTask(request));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
