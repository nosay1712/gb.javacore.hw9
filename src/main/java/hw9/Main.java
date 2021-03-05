package hw9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {
        List<Course> course = new ArrayList();
        Course setCourse = new Course("Информатика");
        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student("Петр", course = new ArrayList<>(Arrays.asList(
                        new Course("Высшая математика"),
                        new Course("Анатомия"),
                        new Course("Иностранный язык"),
                        new Course("Химия")

                ))),
                new Student("Мария", course = new ArrayList<>(Arrays.asList(
                        new Course("Психология"),
                        new Course("Информатика"),
                        new Course("История"),
                        new Course("Финансы")

                ))),
                new Student("Александр", course = new ArrayList<>(Arrays.asList(
                        new Course("Информатика"),
                        new Course("История"),
                        new Course("Физика"),
                        new Course("Биология")

                ))),
                new Student("Алексей", course = new ArrayList<>(Arrays.asList(
                        new Course("Информатика"),
                        new Course("История")
                ))),
                new Student("Екатерина", course = new ArrayList<>(Arrays.asList(
                        new Course("Информатика"),
                        new Course("История"),
                        new Course("Физика")

                )))));

        List<String> unique = uniqueCourses(students.stream());
        List<String> studCourses = studentCourses(students.stream(), setCourse);
        List<String> mostEducated = mostWanted(students.stream());
        System.out.println(unique);
        System.out.println(studCourses);
        System.out.println(mostEducated);


    }

    static List<String> uniqueCourses(Stream<Student> students) {
        return students.map(Student::getAllCourses)
                .flatMap(List::stream)
                .map(Course::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    static List<String> studentCourses(Stream<Student> students, Course SetCourse) {
        return students.filter(student -> student.getAllCourses().stream().anyMatch(course -> course.name == SetCourse.name))
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    static List<String> mostWanted(Stream<Student> students) {
        return students
                .sorted(Comparator.comparingInt(o -> o.getAllCourses().size() * -1))
                .limit(3)
                .map(Student::getName)
                .collect(Collectors.toList());
    }

}
