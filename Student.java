package tracker;

public class Student extends Person {
    private final String studentId;
    private final String course;

    public Student(String studentId, String name, String course) {
        super(name);
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        if (course == null || course.isBlank()) {
            throw new IllegalArgumentException("Course cannot be empty.");
        }
        this.studentId = studentId.trim();
        this.course = course.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return studentId + " | " + getName() + " | " + course;
    }
}
