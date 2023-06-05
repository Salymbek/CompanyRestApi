package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.response.company.CompanyResponse;
import peaksoft.model.Company;
import peaksoft.model.Instructor;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {


    boolean existsByPhoneNumber(String phoneNumber);

    @Query("select new peaksoft.dto.response.company.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber) from Company c")
    List<CompanyResponse>findAllCompany();

    @Query("select new peaksoft.dto.response.company.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber) from Company c where c.id=:id")
    Optional<CompanyResponse> findByCompanyId(Long id);

    @Query("select a.courseName from Company c join c.courses a where a.id = :companyId")
    List<String> findAllCourses(Long companyId);
    @Query("select g.groupName from Company c join c.courses a join a.groups g where g.id = :companyId")
    List<String> findAllGroups(Long companyId);
    @Query("select i.firstName from Company c join c.instructors i where i.id = :companyId")
    List<String> findAllInstructors(Long companyId);
    @Query("select count(s.id) from Company c join c.courses cc join cc.groups g join g.students s where c.id=:companyId")
    int studentCounter(@Param("companyId") Long companyId);

}