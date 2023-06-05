package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.group.GroupRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.group.CountStudentByGroup;
import peaksoft.dto.response.group.GroupInfo;
import peaksoft.dto.response.group.GroupResponse;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.NotFoundException;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;


    public GroupServiceImpl(GroupRepository groupRepository, CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public List<GroupResponse> findAll() {
        return groupRepository.findAllGroup();
    }

    @Override
    public GroupInfo getById(Long id) {
        return groupRepository.findByGroupId(id).orElseThrow(
                ()-> new NotFoundException("Group with id: "+id+" not found!")
        );
    }

    @Override
    public SimpleResponse saveGroup(Long courseId, GroupRequest request) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                ()-> new NotFoundException("Course with id: "+courseId+" not found!"));
        Group group = Group.builder()
                .groupName(request.groupName())
                .imageLink(request.imageLink())
                .description(request.description())
                .build();

        if (groupRepository.existsByGroupName(request.groupName())) {
            throw new AlreadyExistException(String.format(
                    "Group with name: %s is exists", request.groupName()
            ));
        }

        course.getGroups().add(group);
        groupRepository.save(group);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Group whit name: %s successfully save!",group.getGroupName()))
                .build();
    }

    @Override
    public SimpleResponse deleteGroup(Long id) {
        if (!groupRepository.existsById(id)){
            throw new NotFoundException("Group with id - " + id + " doesn't exists!");
        }

        Group group = groupRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Group with id: "+id+" not found!"));
        List<Course>courses = group.getCourses();

        for (Course course: courses){
            course.getGroups().remove(group);
        }
        group.setCourses(null);
        groupRepository.delete(group);


        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Group whit id: %s successfully deleted!",id))
                .build();
    }

    @Override
    public SimpleResponse update(Long id, GroupRequest request) {

        Group group = groupRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Group with id: "+id+" not found!"));

        group.setGroupName(request.groupName());
        group.setImageLink(request.imageLink());
        group.setDescription(request.description());

        groupRepository.save(group);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Group whit id: %s successfully updated!",id))
                .build();
    }

    @Override
    public CountStudentByGroup counter(Long groupId) {
        return CountStudentByGroup.builder()
                .counterStudent(groupRepository.countStudents(groupId).counterStudent())
                .build();
    }
}
