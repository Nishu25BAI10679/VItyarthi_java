package tracker;

import java.time.LocalDate;

public class Task {
    private final String taskId;
    private final String title;
    private final String studentId;
    private final LocalDate dueDate;
    private String status;

    public Task(String taskId, String title, String studentId, LocalDate dueDate) {
        if (taskId == null || taskId.isBlank() || title == null || title.isBlank()
                || studentId == null || studentId.isBlank() || dueDate == null) {
            throw new IllegalArgumentException("Task details cannot be empty.");
        }
        this.taskId = taskId.trim();
        this.title = title.trim();
        this.studentId = studentId.trim();
        this.dueDate = dueDate;
        this.status = "Pending";
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getStudentId() {
        return studentId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void markCompleted() {
        status = "Completed";
    }

    public boolean isOverdue() {
        return "Pending".equals(status) && dueDate.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return taskId + " | " + title + " | student=" + studentId
                + " | due=" + dueDate + " | " + status;
    }

    public String toCsv() {
        return taskId + "," + title.replace(",", " ") + "," + studentId + ","
                + dueDate + "," + status;
    }
}
