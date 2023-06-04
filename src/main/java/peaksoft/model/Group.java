package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "groups")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    @Id
    @SequenceGenerator(
            name = "group_id_gen",
            sequenceName = "group_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            generator = "group_id_gen",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;


    @OneToMany(cascade = {PERSIST,REFRESH,MERGE,DETACH},mappedBy = "group")
    private List<Student>students;

    @ManyToMany(cascade = {PERSIST,REFRESH,MERGE,DETACH},mappedBy = "groups")
    private List<Course>courses;

}
