import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ParallelStreamCollectMapAdvancedExample {
    static class Student {
        private String name;
        private Map<String, Integer> grades;

        public Student(String name, Map<String, Integer> grades) {
            this.name = name;
            this.grades = grades;
        }

        public Map<String, Integer> getGrades() {
            return grades;
        }
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        Map<String, Double> averageGrades = students.parallelStream()
                .flatMap(student -> student.getGrades().entrySet().stream())
                .collect(java.util.stream.Collectors.groupingBy(
                        Map.Entry::getKey,
                        java.util.stream.Collectors.averagingDouble(Map.Entry::getValue)
                ));

        System.out.println("Средние оценки по предметам:");
        averageGrades.forEach((subject, average) -> {
            System.out.println(subject + ": " + average);
        });
    }
}