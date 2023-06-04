package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "courses")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_id_gen",
            sequenceName = "course_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            generator = "course_id_gen",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;

    @ManyToOne(cascade = {PERSIST,REFRESH,MERGE,DETACH})
    private Company company;

    @OneToMany(cascade = {PERSIST,REFRESH,MERGE,DETACH},mappedBy = "course")
    private List<Lesson>lessons;


    @ManyToOne(cascade = {PERSIST,REFRESH,MERGE,DETACH})
    private Instructor instructor;

    @ManyToMany(cascade = {PERSIST,REFRESH,MERGE,DETACH})
    List<Group>groups;


}
