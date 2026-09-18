package tracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileStore {
    private final Path studentFile = Path.of("data", "students.csv");
    private final Path taskFile = Path.of("data", "tasks.csv");

    public FileStore() {
        try {
            Files.createDirectories(Path.of("data"));
            Files.createDirectories(Path.of("out"));
            if (!Files.exists(studentFile)) Files.createFile(studentFile);
            if (!Files.exists(taskFile)) Files.createFile(taskFile);
        } catch (IOException e) {
            throw new IllegalStateException("Could not prepare data files.", e);
        }
    }

    public void saveStudents(List<Student> students) {
        List<String> lines = new ArrayList<>();
        for (Student s : students) {
            lines.add(s.getStudentId() + "," + clean(s.getName()) + "," + clean(s.getCourse()));
        }
        write(studentFile, lines);
    }

    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        for (String line : read(studentFile)) {
            String[] p = line.split(",", 3);
            if (p.length == 3 && !p[0].isBlank()) {
                try {
                    students.add(new Student(p[0], p[1], p[2]));
                } catch (IllegalArgumentException ignored) {
                    // Ignore malformed saved rows.
                }
            }
        }
        return students;
    }

    public void saveTasks(List<Task> tasks) {
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) lines.add(t.toCsv());
        write(taskFile, lines);
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        for (String line : read(taskFile)) {
            String[] p = line.split(",", 5);
            if (p.length == 5 && !p[0].isBlank()) {
                try {
                    Task task = new Task(p[0], p[1], p[2], java.time.LocalDate.parse(p[3]));
                    if ("Completed".equals(p[4])) task.markCompleted();
                    tasks.add(task);
                } catch (Exception ignored) {
                    // Ignore malformed saved rows.
                }
            }
        }
        return tasks;
    }

    private List<String> read(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalStateException("Could not read " + path + ".", e);
        }
    }

    private void write(Path path, List<String> lines) {
        try {
            Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("Could not write " + path + ".", e);
        }
    }

    private String clean(String value) {
        return value.replace(",", " ").trim();
    }
}
