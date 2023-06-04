package peaksoft.service;

import peaksoft.dto.request.group.GroupRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.group.CountStudentByGroup;
import peaksoft.dto.response.group.GroupInfo;
import peaksoft.dto.response.group.GroupResponse;

import java.util.List;

public interface GroupService {
    List<GroupResponse> findAll();
    GroupInfo getById (Long id);
    SimpleResponse saveGroup(Long courseId, GroupRequest request);
    SimpleResponse deleteGroup(Long id);
    SimpleResponse update(Long id, GroupRequest request);
    CountStudentByGroup counter (Long groupId);
}
