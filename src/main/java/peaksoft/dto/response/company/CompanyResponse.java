package peaksoft.dto.response.company;

public record CompanyResponse(
        Long id,
        String name,
        String country,
        String address,
        String phoneNumber
){

}