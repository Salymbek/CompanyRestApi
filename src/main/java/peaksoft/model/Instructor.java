package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "instructors")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Instructor {
    @Id
    @SequenceGenerator(
            name = "instructor_id_gen",
            sequenceName = "instructor_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            generator = "instructor_id_gen",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;

    @ManyToMany(cascade = {PERSIST,REFRESH,MERGE,DETACH})
    private List<Company>companies;

    public void addCompany(Company company){
        if (companies == null){
            companies = new ArrayList<>();
        }
        companies.add(company);
    }

    @OneToMany(cascade = {PERSIST,REFRESH,MERGE,DETACH})
    private List<Course>courses;
}
