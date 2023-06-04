package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.group.CountStudentByGroup;
import peaksoft.dto.response.group.GroupInfo;
import peaksoft.dto.response.group.GroupResponse;
import peaksoft.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select new peaksoft.dto.response.group.GroupResponse(g.id,g.groupName,g.description)from Group g")
    List<GroupResponse> findAllGroup();

    @Query("select new peaksoft.dto.response.group.GroupInfo(g.id,g.groupName,g.imageLink,g.description)from Group g")
    Optional<GroupInfo> findByGroupId(Long id);

    @Query("select new peaksoft.dto.response.group.CountStudentByGroup ((count (s)))from Group g join g.students s where g.id = :id")
    CountStudentByGroup countStudents (Long id);
}