package peaksoft.dto.response.instructor;

public record InstructorResponse(
        Long id,
        String fullName,
        String phoneNumber,
        String specialization
) {
}
