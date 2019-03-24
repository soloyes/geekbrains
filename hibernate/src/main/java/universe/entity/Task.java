package universe.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
@NoArgsConstructor
public class Task {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "duration")
    private int duration;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "score_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "score_id")
    )
    private List<Score> scores;

    public Task(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }
}
