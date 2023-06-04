package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Task {
    @Id
    @SequenceGenerator(
            name = "task_id_gen",
            sequenceName = "task_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            generator = "task_id_gen",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String taskName;
    private String taskText;
    private LocalDate deadLine;

    @ManyToOne(cascade = {PERSIST,REFRESH,MERGE,DETACH})
    private Lesson lesson;

}
