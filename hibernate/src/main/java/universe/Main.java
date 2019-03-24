package universe;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import universe.entity.Score;
import universe.entity.Student;
import universe.entity.Task;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Task.class)
                .addAnnotatedClass(Score.class)
                .buildSessionFactory();
             Session session = factory.getCurrentSession()
        ) {

            //Какие курсы проходит студент? А вот такиэ:
            session.beginTransaction();
            Student tmpStudent = session.get(Student.class, 1);
            tmpStudent.getScores().stream().
                    flatMap(score -> score.getTasks().stream()).
                    collect(Collectors.toList()).stream()
                    .map(Task::getName).forEach(System.out::println);

            //Баллы по определенному студенту? А вот можно и так:
            String query = "SELECT c.name, SUM(b.score) FROM universe.score_task a \n" +
                    "JOIN universe.score b ON a.score_id = b.id\n" +
                    "JOIN universe.task c ON  a.task_id = c.id\n" +
                    "WHERE b.student_id = \"1\" GROUP BY c.name";

            List list = session.createNativeQuery(query).list();
            System.out.println(Arrays.deepToString(list.toArray()));

            //Баллы по определенному курсу? Вот они:
            Task task = session.get(Task.class, 2);

            Map<String, Integer> map = new HashMap<>();
            for (Score s : task.getScores()) {
                map.putIfAbsent(s.getStudent().getName(), s.getScore());
                map.computeIfPresent(s.getStudent().getName(), (key, value) -> value += map.get(key));
            }

            System.out.println(task.getName() + ":");
            map.entrySet().forEach(System.out::println);
            session.getTransaction().commit();
        }
    }
}
