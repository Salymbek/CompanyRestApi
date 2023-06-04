package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.student.ApplicationRequest;
import peaksoft.dto.request.student.RegisterRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.student.StudentResponse;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.NotFoundException;
import peaksoft.model.Student;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;


    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }


    @Override
    public SimpleResponse saveUser(RegisterRequest request) {

        if (studentRepository.existsByEmail(request.email())) {
            throw new AlreadyExistException(String.format(
                    "User with email: %s is exists", request.email()
            ));
        }
        if (studentRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new AlreadyExistException(String.format(
                    "User with phone number: %s is exists", request.phoneNumber()
            ));
        }

        Student student = new Student();
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setPhoneNumber(request.phoneNumber());
        student.setEmail(request.email());
        student.setStudyFormat(request.studyFormat());
        student.setIsBlocked(false);

        studentRepository.save(student);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Your application has been successfully sent!")
                .build();
    }

    @Override
    public List<StudentResponse> findAll() {
        return studentRepository.findAllStudentsIsTrue();
    }

    @Override
    public List<StudentResponse> getAllApplications() {
        return studentRepository.findAllStudentsIsBlock();
    }

    @Override
    public SimpleResponse applicationAccept(Long restId, ApplicationRequest request) {

        Student student = studentRepository.findById(request.studentId()).orElseThrow(
                ()-> new NotFoundException(String.format("User with id - %s is not found!" + request.studentId())));

        if (request.accept()){
            student.setIsBlocked(true);
            student.setGroup(groupRepository.findById(restId).orElseThrow(()->{
                throw new NotFoundException("Group not found!");
            }));
            return new SimpleResponse(
                    HttpStatus.ACCEPTED,
                    String.format("User - %s is accepted!", student.getEmail())
            );
        } else {
            studentRepository.delete(student);
            return new SimpleResponse(
                    HttpStatus.NOT_ACCEPTABLE,
                    String.format("User - %s is rejected!", student.getEmail())
            );
        }


    }

    @Override
    public List<StudentResponse> getFilter(String studyFormat) {
        Student student = new Student();
        if (studyFormat.equals("ONLINE")) return studentRepository.getOnline();
        else if (studyFormat.equals("OFFLINE")) return studentRepository.getOffline();

        return Collections.singletonList(StudentResponse.builder()
                .id(student.getId())
                .fullName(student.getFirstName()+" "+student.getLastName())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .isBlocked(student.getIsBlocked())
                .studyFormat(student.getStudyFormat())
                .build());
    }

    @Override
    public SimpleResponse update(Long id, RegisterRequest request) {
        for (Student student : studentRepository.findAll()) {
            if (!student.getId().equals(id) && student.getEmail().equals(request.email())) {
                throw new NotFoundException(String.format(
                        "Student with login: %s is exists", request.email()
                ));
            }
        }

        Student student = studentRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(String.format("User with id - %s is not found!"+id)));

        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setPhoneNumber(request.phoneNumber());
        student.setEmail(request.email());
        student.setStudyFormat(request.studyFormat());

        studentRepository.save(student);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Student with id - %s is updated!",id))
                .build();

    }

    @Override
    public SimpleResponse deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Student with id - %s is not found!", id))
                    .build();
        }
        studentRepository.deleteById(id);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("User with id - %s is deleted!", id)
        );
    }

    @Override
    public StudentResponse findByStudentId(Long id) {
        return studentRepository.findByIdStudent(id).orElseThrow(
                ()-> new NotFoundException("Student with id: "+id+" not found!")
        );
    }

}
