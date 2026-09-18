package tracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReportGenerator {
    public void exportTasks(List<Task> tasks) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<?> future = executor.submit(() -> writeReport(tasks));
            future.get();
        } catch (Exception e) {
            throw new IllegalStateException("Could not generate report.", e);
        } finally {
            executor.shutdown();
        }
    }

    private void writeReport(List<Task> tasks) {
        Path report = Path.of("out", "task-report.csv");
        try {
            Files.createDirectories(report.getParent());
            List<String> lines = new java.util.ArrayList<>();
            lines.add("Task ID,Title,Student ID,Due Date,Status");
            for (Task task : tasks) lines.add(task.toCsv());
            Files.write(report, lines);
            System.out.println("Report: " + report);
        } catch (IOException e) {
            throw new IllegalStateException("Could not write report.", e);
        }
    }
}
