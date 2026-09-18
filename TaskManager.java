package tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final FileStore store;
    private final List<Student> students;
    private final List<Task> tasks;

    public TaskManager(FileStore store) {
        this.store = store;
        this.students = new ArrayList<>(store.loadStudents());
        this.tasks = new ArrayList<>(store.loadTasks());
    }

    public void addStudent(Student student) {
        if (students.stream().anyMatch(s -> s.getStudentId().equalsIgnoreCase(student.getStudentId()))) {
            throw new IllegalArgumentException("Student ID already exists.");
        }
        students.add(student);
        store.saveStudents(students);
    }

    public void addTask(Task task) {
        if (students.stream().noneMatch(s -> s.getStudentId().equalsIgnoreCase(task.getStudentId()))) {
            throw new IllegalArgumentException("Student does not exist.");
        }
        if (tasks.stream().anyMatch(t -> t.getTaskId().equalsIgnoreCase(task.getTaskId()))) {
            throw new IllegalArgumentException("Task ID already exists.");
        }
        tasks.add(task);
        store.saveTasks(tasks);
    }

    public void completeTask(String taskId) {
        Task task = findTask(taskId);
        task.markCompleted();
        store.saveTasks(tasks);
    }

    public List<Student> getStudents() {
        return List.copyOf(students);
    }

    public List<Task> getTasks() {
        return List.copyOf(tasks);
    }

    public List<Task> getPendingTasks() {
        return tasks.stream().filter(t -> "Pending".equals(t.getStatus())).toList();
    }

    public List<Task> getOverdueTasks() {
        return tasks.stream().filter(Task::isOverdue).toList();
    }

    public Task findTask(String taskId) {
        return tasks.stream()
                .filter(t -> t.getTaskId().equalsIgnoreCase(taskId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task not found."));
    }

    public Task createTask(String taskId, String title, String studentId, LocalDate dueDate) {
        return new Task(taskId, title, studentId, dueDate);
    }
}
