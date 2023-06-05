package peaksoft.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.group.GroupRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.group.CountStudentByGroup;
import peaksoft.dto.response.group.GroupInfo;
import peaksoft.dto.response.group.GroupResponse;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupApi {

    private final GroupService groupService;

    public GroupApi(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<GroupResponse> findAllGroups (){
        return groupService.findAll();
    }

    @PostMapping("/courseId/{courseId}")
    public SimpleResponse save (@PathVariable Long courseId,
                                @RequestBody @Valid GroupRequest request){
        return groupService.saveGroup(courseId, request);
    }

    @GetMapping("/{id}")
    public GroupInfo getById (@PathVariable Long id){
        return groupService.getById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse update (@PathVariable Long id,
                                  @RequestBody @Valid GroupRequest request){
        return groupService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return groupService.deleteGroup(id);
    }


    @GetMapping("/count/{groupId}")
    public CountStudentByGroup count (@PathVariable Long groupId){
        return groupService.counter(groupId);
    }
}
