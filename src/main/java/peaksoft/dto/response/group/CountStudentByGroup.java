package peaksoft.dto.response.group;


import lombok.Builder;


@Builder
public record CountStudentByGroup(
        Integer counter
) {
}
