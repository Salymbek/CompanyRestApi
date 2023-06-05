package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.company.CompanyRequest;
import peaksoft.dto.response.company.CompanyInfo;
import peaksoft.dto.response.company.CompanyResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.exception.BadCredentialException;
import peaksoft.exception.NotFoundException;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;
import peaksoft.service.CompanyService;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;


    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyResponse> findAll() {
        return companyRepository.findAllCompany();
    }

    @Override
    public CompanyResponse getById (Long id){
        return companyRepository.findByCompanyId(id).orElseThrow(
                ()-> new NotFoundException("Company is id: "+id+" not found!!!"));
    }
    @Override
    public SimpleResponse saveCompany(CompanyRequest request){

        Company company = Company.builder()
                .name(request.name())
                .country(request.country())
                .address(request.address())
                .phoneNumber(request.phoneNumber())
                .build();
        companyRepository.save(company);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Company whit name: %s successfully save!",company.getName()))
                .build();
    }

    @Override
    public SimpleResponse deleteCompany(Long id){
        if (!companyRepository.existsById(id)){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Company with id: %d is not found",id))
                    .build();
        }

        companyRepository.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Company with id: %d is successfully deleted!",id))
                .build();

    }

    @Override
    public SimpleResponse update(Long id, CompanyRequest request){
        Company company = companyRepository.findById(id).orElseThrow(
                ()-> new BadCredentialException("Company is id: "+id+" not found!!!"));

        company.setName(request.name());
        company.setCountry(request.country());
        company.setAddress(request.address());
        company.setPhoneNumber(request.phoneNumber());

        companyRepository.save(company);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Company is id: %s successfully updated!",id))
                .build();
    }

    @Override
    public CompanyInfo getInfo(Long id) {

        Company company = companyRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Company with id: "+id+" not found!"));

        return CompanyInfo.builder().id(company.getId())
                .name(company.getName())
                .country(company.getCountry())
                .address(company.getAddress())
                .phoneNumber(company.getPhoneNumber())
                .courseNames(companyRepository.findAllCourses(id))
                .groupNames(companyRepository.findAllGroups(id))
                .instNames(companyRepository.findAllInstructors(id))
                .numberOfStudent(companyRepository.studentCounter(id))
                .build();
    }


}
