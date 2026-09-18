# Student Task Tracker

A small, practical Java command-line application for managing students, assignments, and deadlines.

The project is designed to demonstrate core Java concepts in one simple working system rather than trying to build a large task-management platform.

## 1. Project Overview

Students often keep assignment details and deadlines in different places. This makes it easy to forget tasks or miss due dates.

The **Student Task Tracker** provides one simple place to:

- add student details
- assign tasks to students
- view students and tasks
- mark tasks as completed
- identify overdue pending tasks
- save data locally
- export a task report as a CSV file

The application runs through a command-line menu and does not require a graphical interface or database.

## 2. Problem Statement

Students need a simple way to maintain assignment information and deadlines without depending on multiple notes or applications.

This project solves that problem by providing a lightweight Java application that stores student and task information, checks task status, and generates a report.

## 3. Main Features

### Student Management
- Add a new student.
- View all saved students.
- Prevent duplicate student IDs.
- Validate student name, ID, and course details.

### Task Management
- Add a task for an existing student.
- Store task title and due date.
- View all tasks.
- Mark a task as completed.
- Detect pending tasks whose due date has passed.
- Prevent duplicate task IDs.
- Prevent tasks from being assigned to unknown students.

### Data Storage
- Student information is stored in `data/students.csv`.
- Task information is stored in `data/tasks.csv`.
- The application automatically creates the required `data` and `out` folders.

### Report Generation
- Export the current task list to `out/task-report.csv`.
- The report contains:
  - Task ID
  - Title
  - Student ID
  - Due Date
  - Status
- Report generation uses a Java `ExecutorService` to demonstrate basic concurrency.

## 4. How the Application Works

The program starts by creating a `TaskManager` with a `FileStore`. Existing students and tasks are loaded from the CSV files.

The user then gets the following menu:

```text
1. Add student
2. List students
3. Add task
4. List tasks
5. Complete task
6. View overdue tasks
7. Export report
8. Exit
```

### Typical flow

1. Add a student.
2. Add a task using that student's ID.
3. View the task list.
4. Complete the task when it is finished.
5. Check overdue tasks when needed.
6. Export the current task list as a CSV report.

The application also includes a **demo mode** that creates sample data when needed.

## 5. Project Structure

The Java source files belong in the `src/tracker/` package structure:

```text
Project/
├── src/
│   └── tracker/
│       ├── App.java
│       ├── Person.java
│       ├── Student.java
│       ├── Task.java
│       ├── TaskManager.java
│       ├── FileStore.java
│       ├── ReportGenerator.java
│       └── ValidationTest.java
│
├── data/
│   ├── students.csv
│   └── tasks.csv
│
├── out/
│   └── task-report.csv
│
├── run.sh
├── test.sh
├── README.md
└── statement.md
```

The `data/` and `out/` folders are created automatically by the application when required.

## 6. Class Documentation

### `App.java`

`App` is the entry point of the program.

Responsibilities:
- starts the application
- displays the main menu
- reads user input using `Scanner`
- calls the required `TaskManager` operations
- handles user-facing errors
- supports demo mode

### `Person.java`

`Person` is the base class for common person information.

It stores the student's name and validates that the name is not empty.

### `Student.java`

`Student` extends `Person`.

It adds:
- Student ID
- Course

It also validates the student ID and course before creating a student object.

### `Task.java`

`Task` represents one assignment/task.

It stores:
- Task ID
- Title
- Student ID
- Due date
- Status

A new task starts with the status **Pending**. A task can later be changed to **Completed**.

The class also checks whether a pending task is overdue.

### `TaskManager.java`

`TaskManager` contains the main application logic.

It:
- loads students and tasks
- adds students
- adds tasks
- checks duplicate IDs
- checks whether a student exists before adding a task
- completes tasks
- returns the task list
- finds overdue tasks
- finds a task by ID

### `FileStore.java`

`FileStore` handles local file storage.

It:
- creates the required folders/files
- saves students to CSV
- loads students from CSV
- saves tasks to CSV
- loads tasks from CSV
- ignores malformed saved rows instead of stopping the whole application

### `ReportGenerator.java`

`ReportGenerator` creates the task report.

It:
- receives the current task list
- uses an `ExecutorService` for the report-generation step
- creates `out/task-report.csv`
- writes a CSV header
- writes every task to the report

### `ValidationTest.java`

`ValidationTest` performs basic validation checks.

The current tests verify that:
- an empty student ID is rejected
- a task cannot be created for an unknown student

## 7. Java Concepts Demonstrated

This project brings several Java concepts together:

### Classes and Objects
The project uses separate classes for people, students, tasks, storage, management, reporting, and application control.

### Inheritance
`Student` inherits from `Person`.

### Encapsulation
Class fields are kept private and accessed through methods such as getters.

### Collections
`ArrayList` and Java streams are used to store and process students and tasks.

### Exception Handling
`IllegalArgumentException`, `IllegalStateException`, `IOException`, and related errors are handled where appropriate.

### File I/O
The project uses `java.nio.file` to read and write CSV files.

### Date Handling
`LocalDate` is used for task due dates and overdue checking.

### Basic Concurrency
`ExecutorService` and `Future` are used by the report generator.

### Command-Line Input
`Scanner` is used to read user input from the terminal.

## 8. Input Validation

The project includes validation to prevent common data errors.

Examples:

- empty names are rejected
- empty student IDs are rejected
- empty courses are rejected
- empty task details are rejected
- duplicate student IDs are rejected
- duplicate task IDs are rejected
- tasks for unknown students are rejected

This helps keep the saved data consistent.

## 9. Data Format

### Student CSV

Student records are stored in this general format:

```text
Student ID,Name,Course
```

### Task CSV

Task records are stored as:

```text
Task ID,Title,Student ID,Due Date,Status
```

The report uses the same task information and is written to:

```text
out/task-report.csv
```

## 10. Requirements

- Java JDK 17 or later
- Terminal/command-line environment
- No external Java libraries are required

## 11. How to Run

From the project directory:

```bash
./run.sh
```

If the script does not have execute permission:

```bash
bash run.sh
```

### Demo Mode

To run the built-in demonstration:

```bash
./run.sh demo
```

or:

```bash
bash run.sh demo
```

### Validation Tests

Run the validation checks with:

```bash
./test.sh
```

or:

```bash
bash test.sh
```

The test script compiles the Java source files and runs `ValidationTest`.

## 12. Example Usage

A normal session can look like this:

```text
=== Student Task Tracker ===

1. Add student
2. List students
3. Add task
4. List tasks
5. Complete task
6. View overdue tasks
7. Export report
8. Exit

Choose: 1
Student ID: S101
Name: Aman
Course: CSE
Student added.

Choose: 3
Task ID: T101
Title: Java Assignment
Student ID: S101
Due date (YYYY-MM-DD): 2026-09-25
Task added.

Choose: 4
T101 | Java Assignment | student=S101 | due=2026-09-25 | Pending

Choose: 5
Task ID: T101
Task marked completed.

Choose: 7
Report: out/task-report.csv
```

The exact displayed output may vary depending on the saved data and current date.

## 13. Design Summary

The project follows a simple separation of responsibilities:

```text
User
  |
  v
App.java
  |
  v
TaskManager.java
  |
  +------> Student / Task
  |
  +------> FileStore.java
  |           |
  |           +----> data/*.csv
  |
  +------> ReportGenerator.java
              |
              +----> out/task-report.csv
```

This structure keeps input handling, business logic, storage, and reporting separate and easier to understand.

## 14. Current Scope

The project intentionally stays small and focused.

Included:
- command-line interface
- student management
- task management
- completion tracking
- overdue checking
- CSV storage
- CSV report generation
- basic validation
- basic concurrency demonstration

Not included:
- graphical user interface
- web interface
- database server
- login/authentication
- notifications or reminders

## 15. Future Improvements

Possible extensions for a larger version include:

- user login and roles
- task filtering and searching
- reminder notifications
- priority levels
- database support
- graphical or web interface

These are outside the current project scope.

## 16. Why This Project Is Useful

The main value of this project is not its size. It shows how different Java concepts can work together in one practical application.

Instead of learning classes, inheritance, collections, file handling, exceptions, dates, and concurrency as isolated topics, the project connects them through a real student task-management use case.

## 17. Quick Explanation for Presentation

A simple way to explain the project is:

> "Our project is a Java-based Student Task Tracker. It is a command-line application used to store students, assign tasks, track completion, identify overdue tasks, and export task data to a CSV report. We used object-oriented programming with classes such as Person, Student, and Task, along with collections, exception handling, file I/O, LocalDate, and basic concurrency. The data is stored locally in CSV files, so the project does not need a database."

## 18. Project Status

The project currently provides the core student and task-management features described above, along with local CSV storage, validation, testing, demo mode, and report generation.
