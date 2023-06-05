package peaksoft.api;

import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.instructor.InstructorRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.group.CountStudentByGroup;
import peaksoft.dto.response.instructor.GroupByInstructorDetails;
import peaksoft.dto.response.instructor.InstructorResponse;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorApi {

    private final InstructorService instructorService;


    public InstructorApi(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<InstructorResponse> getAllInstructor() {
        return instructorService.getAllInstructors();
    }

    @PostMapping("/courseId/{courseId}")
    public SimpleResponse saveInstructor(@PathVariable Long courseId, @RequestBody InstructorRequest instructorRequest) {
        return instructorService.saveInstructor(courseId, instructorRequest);
    }

    @GetMapping("/{id}")
    public InstructorResponse getById(@PathVariable Long id) {
        return instructorService.getInstructorById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse updateInstructor(@PathVariable Long id, @RequestBody InstructorRequest instructorRequest) {
        return instructorService.updateInstructor(id, instructorRequest);
    }


    @DeleteMapping("/{id}")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return instructorService.deleteInstructor(id);
    }

    @PostMapping("/assign/instructorId/{instructorId}/companyId/{companyId}")
    public SimpleResponse assignInstructorToCompany(@PathVariable Long instructorId, @PathVariable Long companyId) {
        return instructorService.assignInstructorToCompany(instructorId, companyId);
    }

    @GetMapping("/count/{id}")
    public CountStudentByGroup count(@PathVariable Long id) {
        return instructorService.get(id);
    }

    @GetMapping("/get/{id}")
    public GroupByInstructorDetails info(@PathVariable Long id) {
        return instructorService.infoInstructor(id);
    }

}
