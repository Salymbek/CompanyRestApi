package peaksoft.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "lessons")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Lesson {
    @Id
    @SequenceGenerator(
            name = "lesson_id_gen",
            sequenceName = "lesson_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            generator = "lesson_id_gen",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String lessonName;

    @ManyToOne(cascade = {PERSIST,REFRESH,MERGE,DETACH})
    private Course course;

//    @OneToMany(cascade = {PERSIST,REFRESH,MERGE,DETACH,REMOVE},fetch = FetchType.EAGER)
//    private List<Task>tasks;
//
//    public void addTasks(Task task){
//        if (tasks == null){
//            tasks = new ArrayList<>();
//        }
//        tasks.add(task);
//    }
}
