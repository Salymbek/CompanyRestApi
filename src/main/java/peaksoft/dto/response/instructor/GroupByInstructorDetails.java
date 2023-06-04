package peaksoft.dto.response.instructor;

import lombok.Builder;

import java.util.List;
@Builder
public record GroupByInstructorDetails(
        Long id,
        String fullName,
        String phoneNumber,
        String specialization,
        List<String> groupName,
        int studentCount
) {
}
