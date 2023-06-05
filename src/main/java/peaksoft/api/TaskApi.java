package peaksoft.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.task.TaskRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.task.TaskResponse;
import peaksoft.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskApi {

    private final TaskService taskService;


    public TaskApi(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/lessonId/{lessonId}")
    public SimpleResponse save(@PathVariable Long lessonId,
                               @RequestBody @Valid TaskRequest request){
        return taskService.saveTask(lessonId, request);
    }

    @GetMapping
    public List<TaskResponse> findAll(){
        return taskService.findAll();
    }

    @GetMapping("/{taskId}")
    public TaskResponse getById(@PathVariable Long taskId){
        return taskService.getById(taskId);
    }

    @PutMapping("/{taskId}")
    public SimpleResponse update(@PathVariable Long taskId,
                                 @RequestBody @Valid TaskRequest request){
        return taskService.update(taskId, request);
    }

    @DeleteMapping("/{taskId}")
    public SimpleResponse delete (@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }
}
