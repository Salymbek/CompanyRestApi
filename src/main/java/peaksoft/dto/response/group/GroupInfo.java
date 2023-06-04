package peaksoft.dto.response.group;

public record GroupInfo(
        Long id,
        String groupName,
        String imageLink,
        String description
) {
}
