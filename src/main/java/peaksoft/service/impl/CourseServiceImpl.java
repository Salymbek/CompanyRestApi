package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.course.CourseRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.course.CourseResponse;
import peaksoft.exception.NotFoundException;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.service.CourseService;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;


    public CourseServiceImpl(CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public List<CourseResponse> findAll() {
        return courseRepository.findAllCourse();
    }

    @Override
    public CourseResponse getById(Long id) {
        return courseRepository.findByCourseId(id).orElseThrow(
                ()-> new NotFoundException("Course with id: "+id+" not found!"));
    }

    @Override
    public SimpleResponse saveCourse(Long companyId,CourseRequest request) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                ()-> new NotFoundException("Company with id: "+companyId+" not found!"));

        Course course = Course.builder()
                .courseName(request.courseName())
                .dateOfStart(request.dateOfStart())
                .description(request.description())
                .build();

        course.setCompany(company);
        company.getCourses().add(course);

        courseRepository.save(course);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Course whit name: %s successfully save!",course.getCourseName()))
                .build();
    }

    @Override
    public SimpleResponse deleteCourse(Long id) {

        if (!courseRepository.existsById(id)){
            throw new NotFoundException("Course with id - " + id + " doesn't exists!");
        }

        Course course = courseRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Course with id: "+id+" not found!"));

        courseRepository.delete(course);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Course whit id: %s successfully deleted!",id))
                .build();
    }

    @Override
    public SimpleResponse update(Long id, CourseRequest request) {

        Course course = courseRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Course with id: "+id+" not found!"));

        course.setCourseName(request.courseName());
        course.setDateOfStart(request.dateOfStart());
        course.setDescription(request.description());

        courseRepository.save(course);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Course whit id: %s successfully updated!",id))
                .build();
    }

    @Override
    public List<CourseResponse> sorting(String ascOrDesc) {
        if (ascOrDesc.equals("desc")){
            return courseRepository.descSorting();
        } else {
            return courseRepository.ascSorting();
        }
    }
}
