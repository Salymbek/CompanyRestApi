package peaksoft.service;

import peaksoft.dto.request.task.TaskRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.task.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> findAll();
    TaskResponse getById (Long id);
    SimpleResponse saveTask(Long lessonId,TaskRequest request);
    SimpleResponse deleteTask(Long id);
    SimpleResponse update(Long id, TaskRequest request);

}
