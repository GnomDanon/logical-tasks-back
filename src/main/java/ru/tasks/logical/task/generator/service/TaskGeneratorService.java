package ru.tasks.logical.task.generator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasks.logical.common.dto.CrosswordQuestionItem;
import ru.tasks.logical.common.dto.TestItem;
import ru.tasks.logical.document.entity.Document;
import ru.tasks.logical.document.exception.DocumentNotExistsException;
import ru.tasks.logical.document.service.DocumentService;
import ru.tasks.logical.gpt.service.GPTService;
import ru.tasks.logical.task.converter.CrosswordAttributeConverter;
import ru.tasks.logical.task.converter.CrosswordQuestionsAttributeConverter;
import ru.tasks.logical.task.converter.TestQuestionsAttributeConverter;
import ru.tasks.logical.task.entity.Crossword;
import ru.tasks.logical.task.entity.CrosswordQuestions;
import ru.tasks.logical.task.entity.Task;
import ru.tasks.logical.task.entity.TaskType;
import ru.tasks.logical.task.entity.TestQuestions;
import ru.tasks.logical.task.exception.TaskNotExistsException;
import ru.tasks.logical.task.generator.dto.questions.Question;
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
import ru.tasks.logical.task.generator.service.crossword.CrosswordGenerator;
import ru.tasks.logical.task.repository.TaskRepository;
import ru.tasks.logical.user.entity.User;
import ru.tasks.logical.user.exception.UserNotFoundException;
import ru.tasks.logical.user.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskGeneratorService {
	private final TaskRepository taskRepository;
	private final DocumentService documentService;
	private final UserService userService;
	private final GPTService gptService;
	private final CrosswordGenerator crosswordGenerator;

	private final TestQuestionsAttributeConverter testQuestionsConverter = new TestQuestionsAttributeConverter();
	private final CrosswordQuestionsAttributeConverter crosswordQuestionsConverter = new CrosswordQuestionsAttributeConverter();
	private final CrosswordAttributeConverter crosswordAttributeConverter = new CrosswordAttributeConverter();

	public GenerateTermsResponse generateTerms(GenerateTermsRequest request) throws DocumentNotExistsException,
			UserNotFoundException {

		UUID authorId = request.getAuthorId();
		UUID documentId = request.getDocumentId();
		int termsCount = request.getTermsCount();
		String taskType = request.getTaskType();

		Document document = documentService.findById(documentId);
		String fileName = document.getName();
		byte[] documentContent = document.getContent();

		User author = userService.getById(authorId);

		String[] terms = gptService.generateTerms(fileName, documentContent, termsCount);

		Task task = new Task()
				.setTerms(String.join(" ", terms))
				.setAuthor(author)
				.setDocumentBasedOn(document);

		//Временное решение
		if (taskType.equals("Тест")) {
			task.setTaskType(TaskType.TEST);
		} else {
			task.setTaskType(TaskType.CROSSWORD);
		}

		task = taskRepository.save(task);

		return new GenerateTermsResponse().setTaskId(task.getId()).setTerms(terms);
	}

	public UpdateTermsResponse updateTerms(UpdateTermsRequest request) throws TaskNotExistsException {
		UUID taskId = request.getTaskId();
		String[] terms = request.getTerms();

		Task task = taskRepository.findById(taskId).orElseThrow(TaskNotExistsException::new);
		task.setTerms(String.join(" ", terms));
		taskRepository.save(task);

		return new UpdateTermsResponse().setTaskId(taskId).setTerms(terms);
	}

	public GenerateQuestionsResponse generateQuestions(GenerateQuestionsRequest request) throws TaskNotExistsException,
			DocumentNotExistsException {

		UUID taskId = request.getTaskId();
		int questionsCount = request.getQuestionsCount();

		Task task = taskRepository.findById(taskId).orElseThrow(TaskNotExistsException::new);
		String[] terms = task.getTerms().split(" ");
		UUID documentId = task.getDocumentBasedOn().getId();
		Document document = documentService.findById(documentId);
		String fileName = document.getName();

		TaskType type = task.getTaskType();
		String questionsToSave = "";
		Question[] questionsDto = new Question[questionsCount];
		int realQuestionsCount = 0;
		switch (type) {
			case TEST -> {
				TestItem[] questions = gptService.generateTest(fileName, document.getContent(), terms, questionsCount);
				questionsToSave = testQuestionsConverter.convertToDatabaseColumn(new TestQuestions(questions));
				realQuestionsCount = questions.length;
				for (int i = 0; i < questions.length && i < questionsDto.length; i++) {
					TestItem question = questions[i];
					questionsDto[i] = Question.test(question.getQuestion(), question.getAnswers(), question.correctAnswerIndex());
				}
			}
			case CROSSWORD -> {
				CrosswordQuestionItem[] questions = gptService.generateCrossword(fileName, document.getContent(), terms, questionsCount);
				questionsToSave = crosswordQuestionsConverter.convertToDatabaseColumn(new CrosswordQuestions(questions));
				realQuestionsCount = questions.length;
				for (int i = 0; i < questions.length && i < questionsDto.length; i++) {
					CrosswordQuestionItem question = questions[i];
					questionsDto[i] = Question.crossword(question.getQuestion(), question.getAnswer());
				}
			}
		}
		Question[] realQuestionsDto = new Question[Math.min(realQuestionsCount, questionsCount)];
		for (int i = 0; i < realQuestionsCount && i < questionsDto.length; i++) {
			realQuestionsDto[i] = questionsDto[i];
		}
		task.setQuestions(questionsToSave);
		task.setMaxScore(realQuestionsDto.length);
		taskRepository.save(task);

		return new GenerateQuestionsResponse().setTaskId(taskId).setQuestions(realQuestionsDto);
	}

	public UpdateQuestionsResponse updateQuestions(UpdateQuestionsRequest request) throws TaskNotExistsException {
		UUID taskId = request.getTaskId();
		Question[] questions = request.getQuestions();

		Task task = taskRepository.findById(taskId).orElseThrow(TaskNotExistsException::new);
		TaskType taskType = task.getTaskType();
		String questionsToSave = "";
		switch (taskType) {
			case TEST -> {
				TestItem[] testItems = new TestItem[questions.length];
				for (int i = 0; i < questions.length; i++) {
					Question question = questions[i];
					TestItem testItem = new TestItem(question.getQuestion(), question.getAnswers(), question.getCorrectAnswerIndex());
					testItems[i] = testItem;
				}
				questionsToSave = testQuestionsConverter.convertToDatabaseColumn(new TestQuestions(testItems));
			}
			case CROSSWORD -> {
				CrosswordQuestionItem[] crosswordQuestionItems = new CrosswordQuestionItem[questions.length];
				for (int i = 0; i < questions.length; i++) {
					Question question = questions[i];
					CrosswordQuestionItem crosswordQuestionItem = new CrosswordQuestionItem(question.getQuestion(), question.getAnswers()[0]);
					crosswordQuestionItems[i] = crosswordQuestionItem;
				}
				questionsToSave = crosswordQuestionsConverter.convertToDatabaseColumn(new CrosswordQuestions(crosswordQuestionItems));
			}
		}
		task.setQuestions(questionsToSave);
		task.setMaxScore(questions.length);
		taskRepository.save(task);

		return new UpdateQuestionsResponse().setTaskId(taskId).setQuestions(questions);
	}

	public GenerateTaskResponse generateTask(GenerateTaskRequest request) throws TaskNotExistsException {
		UUID taskId = request.getTaskId();
		String taskName = request.getTaskName();

		Task task = taskRepository.findById(taskId).orElseThrow(TaskNotExistsException::new);
		TaskType taskType = task.getTaskType();

		String content = "";
		switch (taskType) {
			case TEST -> content = task.getQuestions();
			case CROSSWORD -> {
				CrosswordQuestions questions = crosswordQuestionsConverter.convertToEntityAttribute(task.getQuestions());
				Crossword crossword = crosswordGenerator.generateCrossword(questions);
				content = crosswordAttributeConverter.convertToDatabaseColumn(crossword);
				task.setMaxScore(crossword.getAcross().length + crossword.getDown().length);
			}
		}
		task.setName(taskName);
		task.setContent(content);
		taskRepository.save(task);

		return new GenerateTaskResponse().setTaskId(taskId);
	}
}
