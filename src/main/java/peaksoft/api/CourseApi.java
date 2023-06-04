package peaksoft.api;

import jakarta.validation.Valid;
import org.hibernate.LockOptions;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.course.CourseRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.course.CourseResponse;
import peaksoft.model.Course;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseApi {

    private final CourseService courseService;


    public CourseApi(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseResponse> findAllCourse(){
        return courseService.findAll();
    }

    @PostMapping("/{companyId}")
    public SimpleResponse save (@PathVariable Long companyId,
                                @RequestBody @Valid CourseRequest request){
        return courseService.saveCourse(companyId, request);
    }

    @GetMapping("/{id}")
    public CourseResponse findByCourseId(@PathVariable Long id){
        return courseService.getById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody @Valid CourseRequest request){
        return courseService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete (@PathVariable Long id){
        return courseService.deleteCourse(id);
    }

    @GetMapping("/sort")
    public List<CourseResponse>sort(@RequestParam(required = false) String ascOrDesc){
        return courseService.sorting(ascOrDesc);
    }

}
