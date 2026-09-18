package tracker;

import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager(new FileStore());
        if (args.length > 0 && args[0].equalsIgnoreCase("demo")) {
            demo(manager);
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        System.out.println("=== Student Task Tracker ===");

        while (running) {
            System.out.println("\n1. Add student\n2. List students\n3. Add task\n4. List tasks"
                    + "\n5. Complete task\n6. View overdue tasks\n7. Export report\n8. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> addStudent(sc, manager);
                    case "2" -> manager.getStudents().forEach(System.out::println);
                    case "3" -> addTask(sc, manager);
                    case "4" -> manager.getTasks().forEach(System.out::println);
                    case "5" -> {
                        System.out.print("Task ID: ");
                        manager.completeTask(sc.nextLine().trim());
                        System.out.println("Task marked completed.");
                    }
                    case "6" -> manager.getOverdueTasks().forEach(System.out::println);
                    case "7" -> new ReportGenerator().exportTasks(manager.getTasks());
                    case "8" -> running = false;
                    default -> System.out.println("Choose a valid option.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addStudent(Scanner sc, TaskManager manager) {
        System.out.print("Student ID: ");
        String id = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Course: ");
        String course = sc.nextLine();
        manager.addStudent(new Student(id, name, course));
        System.out.println("Student added.");
    }

    private static void addTask(Scanner sc, TaskManager manager) {
        System.out.print("Task ID: ");
        String id = sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Student ID: ");
        String studentId = sc.nextLine();
        System.out.print("Due date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(sc.nextLine().trim());
        manager.addTask(manager.createTask(id, title, studentId, dueDate));
        System.out.println("Task added.");
    }

    private static void demo(TaskManager manager) {
        try {
            if (manager.getStudents().stream().noneMatch(s -> s.getStudentId().equals("S101"))) {
                manager.addStudent(new Student("S101", "Aman", "CSE"));
            }
            if (manager.getTasks().stream().noneMatch(t -> t.getTaskId().equals("T101"))) {
                manager.addTask(new Task("T101", "Java Assignment", "S101", LocalDate.now().plusDays(3)));
            }
            System.out.println("Students:");
            manager.getStudents().forEach(System.out::println);
            System.out.println("Tasks:");
            manager.getTasks().forEach(System.out::println);
            new ReportGenerator().exportTasks(manager.getTasks());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Demo error: " + e.getMessage());
        }
    }
}
