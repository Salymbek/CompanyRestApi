package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.instructor.InstructorRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.group.CountStudentByGroup;
import peaksoft.dto.response.instructor.GroupByInstructorDetails;
import peaksoft.dto.response.instructor.InstructorResponse;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.NotFoundException;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.List;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository, CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public SimpleResponse saveInstructor(Long courseId, InstructorRequest request) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                ()-> new NotFoundException("Course with id: "+courseId+" not found!"));

        if (instructorRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new AlreadyExistException(String.format(
                    "Instructor with phone number: %s is exists", request.phoneNumber()
            ));
        }

        Instructor instructor = Instructor.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .phoneNumber(request.phoneNumber())
                .specialization(request.specialization())
                .build();

        course.setInstructor(instructor);
        instructor.setCourses(List.of(course));

        instructorRepository.save(instructor);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Instructor with full name: %s %s successfully saved!",request.firstName(),request.lastName()))
                .build();
    }

    @Override
    public List<InstructorResponse> getAllInstructors() {
        return instructorRepository.findAllInstructors();
    }

    @Override
    public InstructorResponse getInstructorById(Long instructorId) {
        return instructorRepository.findByIdInst(instructorId).orElseThrow(
                ()-> new NotFoundException("Instructor with id: "+instructorId+" not found!"));
    }

    @Override
    public SimpleResponse updateInstructor(Long instructorId, InstructorRequest request) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                ()-> new NotFoundException("Instructor with id: "+instructorId+" not found!"));
        instructor.setFirstName(request.firstName());
        instructor.setLastName(request.lastName());
        instructor.setPhoneNumber(request.phoneNumber());
        instructor.setSpecialization(request.specialization());

        instructorRepository.save(instructor);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Instructor whit id: %s successfully updated!",instructorId))
                .build();
    }

    @Override
    public SimpleResponse deleteInstructor(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                ()-> new NotFoundException("Instructor with id: "+instructorId+" not found!"));

        instructorRepository.delete(instructor);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Instructor whit id: %s successfully deleted!",instructorId))
                .build();
    }

    @Override
    public SimpleResponse assignInstructorToCompany(Long instructorId, Long companyId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new NotFoundException("Instructor with id " + instructorId + " not found!"));
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new NotFoundException("Company with id " + companyId + " not found!"));

        company.addInstructor(instructor);
        instructor.addCompany(company);

        instructorRepository.save(instructor);
        companyRepository.save(company);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Successfully!")
                .build();
    }

    @Override
    public CountStudentByGroup get(Long id) {
        return CountStudentByGroup.builder()
                .counterStudent(instructorRepository.getAllCount(id))
                .build();
    }

    @Override
    public GroupByInstructorDetails infoInstructor(Long instructorId) {
        Instructor i = instructorRepository.findById(instructorId).orElseThrow(() -> new NullPointerException("Instructor with id: " + instructorId + "is not found!"));
        return GroupByInstructorDetails.builder()
                .id(i.getId())
                .fullName(i.getFirstName()+" "+i.getLastName())
                .phoneNumber(i.getPhoneNumber())
                .specialization(i.getSpecialization())
                .groupName(instructorRepository.getAllInstructorsDetails(instructorId))
                .studentCount(instructorRepository.getAllStudentCount(instructorId)).build();

    }
}
