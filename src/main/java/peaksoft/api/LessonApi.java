package peaksoft.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.lesson.LessonRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.lesson.LessonResponse;
import peaksoft.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("/api/lesson")
public class LessonApi {

    private final LessonService lessonService;

    public LessonApi(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public List<LessonResponse> findAll(){
        return lessonService.findAll();
    }


    @PostMapping("/{courseId}")
    public SimpleResponse save(@PathVariable Long courseId,
                               @RequestBody @Valid LessonRequest request){
        return lessonService.saveLesson(courseId, request);
    }

    @GetMapping("/{id}")
    public LessonResponse findById (@PathVariable Long id){
        return lessonService.getById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse update (@PathVariable Long id,
                                  @RequestBody @Valid LessonRequest request){
        return lessonService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete (@PathVariable Long id){
        return lessonService.deleteLesson(id);
    }

}
