package tracker;

import java.time.LocalDate;

public class ValidationTest {
    public static void main(String[] args) {
        FileStore store = new FileStore();
        TaskManager manager = new TaskManager(store);

        try {
            new Student("", "Test", "CSE");
            System.out.println("FAIL: empty student ID accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: empty student ID rejected");
        }

        try {
            manager.addTask(new Task("T999", "Test", "UNKNOWN", LocalDate.now()));
            System.out.println("FAIL: task for unknown student accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: unknown student rejected");
        }

        System.out.println("Validation tests completed.");
    }
}
