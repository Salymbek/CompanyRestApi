package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @SequenceGenerator(
            name = "company_id_gen",
            sequenceName = "company_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            generator = "company_id_gen",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;

    @ManyToMany(mappedBy = "companies",cascade = {PERSIST,REFRESH,MERGE,DETACH})
    private List<Instructor>instructors;

    public void addInstructor(Instructor instructor){
        if (instructors == null){
            instructors = new ArrayList<>();
        }
        instructors.add(instructor);
    }

    @OneToMany(mappedBy = "company",cascade = ALL)
    private List<Course>courses;

}
