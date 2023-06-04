package peaksoft.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.company.CompanyRequest;
import peaksoft.dto.response.company.CompanyInfo;
import peaksoft.dto.response.company.CompanyResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyApi {
    private final CompanyService companyService;

    public CompanyApi(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> findAll() {
        return companyService.findAll();
    }

    @PostMapping
    public SimpleResponse saveCompany (@RequestBody @Valid CompanyRequest request){
        return companyService.saveCompany(request);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete (@PathVariable Long id){
        return companyService.deleteCompany(id);
    }

    @GetMapping("/{id}")
    public CompanyResponse getById(@PathVariable Long id){
        return companyService.getById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse update (@PathVariable Long id, @RequestBody @Valid CompanyRequest request){
        return companyService.update(id, request);
    }

    @GetMapping("/info/{companyId}")
    public CompanyInfo getByIdCompany(@PathVariable Long companyId) {
        return companyService.getInfo(companyId);
    }
}
