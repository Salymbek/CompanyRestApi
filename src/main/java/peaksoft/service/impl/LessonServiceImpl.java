package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.lesson.LessonRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.lesson.LessonResponse;
import peaksoft.exception.NotFoundException;
import peaksoft.model.Course;
import peaksoft.model.Lesson;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;

import java.util.List;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;


    public LessonServiceImpl(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public List<LessonResponse> findAll() {
        return lessonRepository.findAllLesson();
    }

    @Override
    public LessonResponse getById(Long id) {
        return lessonRepository.findByLessonId(id).orElseThrow(
                ()-> new NotFoundException("Lesson with id: "+id+" not found!")
        );
    }

    @Override
    public SimpleResponse saveLesson(Long courseId,LessonRequest request) {

        Course course = courseRepository.findById(courseId).orElseThrow(
                ()-> new NotFoundException("Course with id: "+courseId+" not found!"));

        Lesson lesson = Lesson.builder()
                .lessonName(request.lessonName())
                .build();

        lesson.setCourse(course);
        course.getLessons().add(lesson);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Lesson whit name: %s successfully save!",lesson.getLessonName()))
                .build();
    }

    @Override
    public SimpleResponse deleteLesson(Long id) {

        if (!lessonRepository.existsById(id)){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Task with id: %s not found", id))
                    .build();
        }

        Lesson lesson = lessonRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Lesson with id: "+id+" not found!"));


        lessonRepository.delete(lesson);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Your lesson with id: %s successfully deleted!",id))
                .build();
    }

    @Override
    public SimpleResponse update(Long id, LessonRequest request) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Lesson with id: "+id+" not found!"));

        lesson.setLessonName(request.lessonName());

        lessonRepository.save(lesson);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("lesson with id: %s successfully updated", id))
                .build();
    }
}
