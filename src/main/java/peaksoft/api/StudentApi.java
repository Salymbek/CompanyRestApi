package peaksoft.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.student.ApplicationRequest;
import peaksoft.dto.request.student.RegisterRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.student.StudentResponse;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentApi {

    private final StudentService studentService;


    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponse> getAll(){
        return studentService.findAll();
    }


    @GetMapping("/{id}")
    public StudentResponse getById (@PathVariable Long id){
        return studentService.findByStudentId(id);
    }

    @PostMapping("/register")
    public SimpleResponse save (@RequestBody @Valid RegisterRequest request){
        return studentService.saveUser(request);
    }

    @PutMapping("/{id}")
    public SimpleResponse updateUser(@PathVariable Long id, @RequestBody @Valid RegisterRequest request){
        return studentService.update(id,request);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteUser(@PathVariable Long id){
        return studentService.deleteById(id);
    }

    @GetMapping("/applications")
    public List<StudentResponse>getAllApplications(){
        return studentService.getAllApplications();
    }

    @PostMapping("/accept/{groupId}")
    SimpleResponse acceptResponse(@PathVariable Long groupId,
                                  @RequestBody ApplicationRequest request){
        return studentService.applicationAccept(groupId, request);
    }


    @GetMapping("/filter")
    public List<StudentResponse> filterByStudyFormat(@RequestParam(required = false) String studyFormat) {
        return studentService.getFilter(studyFormat);
    }



}
