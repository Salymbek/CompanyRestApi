package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.task.TaskRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.task.TaskResponse;
import peaksoft.exception.NotFoundException;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.LessonRepository;
import peaksoft.repository.TaskRepository;
import peaksoft.service.TaskService;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    public TaskServiceImpl(TaskRepository taskRepository, LessonRepository lessonRepository) {
        this.taskRepository = taskRepository;
        this.lessonRepository = lessonRepository;
    }


    @Override
    public List<TaskResponse> findAll() {
        return taskRepository.findAllTask();
    }

    @Override
    public TaskResponse getById(Long id) {
        return taskRepository.findTaskById(id).orElseThrow(
                ()-> new NotFoundException("Task is id: "+id+" not found!!!")
        );
    }

    @Override
    public SimpleResponse saveTask(Long lessonId,TaskRequest request) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
                ()-> new NotFoundException("Lesson is id: "+lessonId+" not found!!!")
        );



        Task task = new Task();
        task.setTaskName(request.taskName());
        task.setTaskText(request.taskText());
        LocalDate currentDate = LocalDate.now();
        task.setDeadLine(currentDate);


        if (request.deadLine().isBefore(currentDate)) {
            throw new IllegalArgumentException("Deadline must be in the future!!!");
        }
//                .taskName(request.taskName())
//                .taskText(request.taskText())
//                .deadLine(request.deadLine())
//                .build();

//

        task.setLesson(lesson);
        taskRepository.save(task);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Task whit name: %s successfully save!",request.taskName()))
                .build();
    }

    @Override
    public SimpleResponse deleteTask(Long id) {

        if (!taskRepository.existsById(id)){
            throw new NotFoundException("Task with id - " + id + " doesn't exists!");
        }

        Task task = taskRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Task with id: "+id+" doesn't exist!"));

        taskRepository.delete(task);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Task with id: %s successfully deleted", id))
                .build();
    }

    @Override
    public SimpleResponse update(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Task with id: "+id+" not found!"));

        task.setTaskName(request.taskName());
        task.setTaskText(request.taskText());
        LocalDate currentDate = LocalDate.now();
        task.setDeadLine(currentDate);
//        task.setTaskName(request.taskName());
//        task.setTaskText(request.taskText());
//        LocalDate currentDate = LocalDate.now();
//        LocalDate deadLine = task.getDeadLine();
//        task.setDeadLine(currentDate);
//
        if (request.deadLine().isBefore(currentDate)) {
            throw new IllegalArgumentException("Deadline must be in the future!!!");
        }
        taskRepository.save(task);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Task with id: %s successfully updated", id))
                .build();
    }
}
