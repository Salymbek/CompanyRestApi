package peaksoft.dto.request.group;

import jakarta.validation.constraints.NotEmpty;

public record GroupRequest(
        @NotEmpty(message = "the group name can't be empty!")
        String groupName,
        @NotEmpty(message = "image link can't be empty!")
        String imageLink,
        @NotEmpty(message = "description can't be empty!")
        String description
) {
}
