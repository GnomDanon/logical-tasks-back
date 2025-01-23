package ru.tasks.logical.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasks.logical.common.dto.CrosswordQuestionItem;
import ru.tasks.logical.common.dto.TestItem;
import ru.tasks.logical.task.converter.CrosswordAttributeConverter;
import ru.tasks.logical.task.converter.CrosswordConverter;
import ru.tasks.logical.task.converter.CrosswordQuestionsAttributeConverter;
import ru.tasks.logical.task.converter.TestQuestionsAttributeConverter;
import ru.tasks.logical.task.dto.Task;
import ru.tasks.logical.task.dto.TaskInfo;
import ru.tasks.logical.task.dto.TaskTypeInfo;
import ru.tasks.logical.task.entity.Crossword;
import ru.tasks.logical.task.entity.CrosswordQuestions;
import ru.tasks.logical.task.entity.TaskType;
import ru.tasks.logical.task.entity.TestQuestions;
import ru.tasks.logical.task.exception.TaskNotExistsException;
import ru.tasks.logical.task.generator.dto.questions.Question;
import ru.tasks.logical.task.mapper.TaskMapper;
import ru.tasks.logical.task.mapper.TaskTypeMapper;
import ru.tasks.logical.task.repository.TaskRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final CrosswordConverter crosswordConverter = new CrosswordConverter();
    private final CrosswordAttributeConverter crosswordAttributeConverter = new CrosswordAttributeConverter();
    private final TestQuestionsAttributeConverter testQuestionsConverter = new TestQuestionsAttributeConverter();
    private final CrosswordQuestionsAttributeConverter crosswordQuestionsConverter = new CrosswordQuestionsAttributeConverter();

    public List<TaskInfo> getTasksByAuthorId(UUID authorId) {
        return taskRepository.findAllByAuthor_Id(authorId)
                .stream()
                .map(TaskMapper::mapTaskToTaskInfo)
                .collect(Collectors.toList());
    }

    public List<TaskTypeInfo> getTaskTypes() {
        return Arrays.stream(TaskType.values())
                .map(TaskTypeMapper::mapTaskTypeToTaskTypeInfo)
                .toList();
    }

    public Task getById(UUID taskId) throws TaskNotExistsException {
        ru.tasks.logical.task.entity.Task task = taskRepository.findById(taskId).orElseThrow(TaskNotExistsException::new);

        TaskInfo taskInfo = TaskMapper.mapTaskToTaskInfo(task);
        String content = task.getContent();
        String questions = task.getQuestions();
        TaskType taskType = task.getTaskType();
        Question[] taskQuestions = new Question[task.getMaxScore()];

        if (content != null) {
            switch (taskType) {
                case TEST -> content = task.getContent();
                case CROSSWORD -> {
                    String taskContent = task.getContent();
                    Crossword crossword = crosswordAttributeConverter.convertToEntityAttribute(taskContent);
                    content = crosswordConverter.convertToString(crossword);
                }
            }
        }

        if (questions != null) {
            switch (taskType) {
                case TEST -> {
                    TestQuestions testQuestions = testQuestionsConverter.convertToEntityAttribute(questions);
                    for (int i = 0; i < testQuestions.getQuestions().length; i++) {
                        TestItem testItem = testQuestions.getQuestions()[i];
                        taskQuestions[i] = Question.test(testItem.getQuestion(), testItem.getAnswers(), testItem.getCorrectAnswer());
                    }
                }
                case CROSSWORD -> {
                    CrosswordQuestions crosswordQuestions = crosswordQuestionsConverter.convertToEntityAttribute(questions);
                    for (int i = 0; i < crosswordQuestions.getQuestions().length; i++) {
                        CrosswordQuestionItem crosswordItem = crosswordQuestions.getQuestions()[i];
                        taskQuestions[i] = Question.crossword(crosswordItem.getQuestion(), crosswordItem.getAnswer());
                    }
                }
            }
        }

        return new Task().setTaskInfo(taskInfo).setContent(content).setQuestion(taskQuestions);
    }
}
