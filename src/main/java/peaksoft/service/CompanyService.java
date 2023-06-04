package peaksoft.service;


import peaksoft.dto.request.company.CompanyRequest;
import peaksoft.dto.response.company.CompanyInfo;
import peaksoft.dto.response.company.CompanyResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;


public interface CompanyService {
    List<CompanyResponse> findAll();
    CompanyResponse getById (Long id);
    SimpleResponse saveCompany(CompanyRequest request);
    SimpleResponse deleteCompany(Long id);
    SimpleResponse update(Long id, CompanyRequest request);
    CompanyInfo getInfo(Long id);
}
