package peaksoft.dto.response.company;

import lombok.Builder;
import peaksoft.model.Instructor;

import java.util.List;
@Builder
public record CompanyInfo(
        Long id,
        String name,
        String country,
        String address,
        String phoneNumber,
        List<String> groupNames,
        List<String> courseNames,
        List<String> instNames,
        Integer numberOfStudent
) {
}
