package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.StudyFormat;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "students")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Student {
    @Id
    @SequenceGenerator(
            name = "student_id_gen",
            sequenceName = "student_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            generator = "student_id_gen",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private Boolean isBlocked;
    @ManyToOne(cascade = {PERSIST,REFRESH,MERGE,DETACH})
    private Group group;
}
