# Student Task Tracker

A simple command-line Java project for keeping track of student assignments and deadlines.

The project is intentionally small. It focuses on using the Java concepts covered in the course without turning the system into a large application.

## What the project does

A student can be added to the system and tasks can then be assigned to that student. Each task has a title, due date, and status. Tasks can be viewed, marked as completed, and checked for overdue items.

The program also saves its data locally and can generate a CSV report of the task list.

## Main Features

### 1. Student Management

- Add a student
- List saved students
- Prevent duplicate student IDs

### 2. Task Management

- Add a task for an existing student
- List tasks
- Mark a task as completed
- Detect overdue pending tasks

### 3. Reporting

- Export the current task list to a CSV file
- Generate the report using a Java executor so the project also demonstrates basic concurrency

## Java Concepts Used

The project uses:

- Classes, objects and constructors
- Inheritance through `Person` and `Student`
- Encapsulation
- Collections such as `ArrayList` and streams
- Exception handling and input validation
- File I/O using `java.nio.file`
- `LocalDate` for deadline handling
- Basic concurrency using `ExecutorService`
- Command-line input using `Scanner`

The goal is to use these concepts where they make sense instead of adding features only to increase the size of the project.

## Project Structure

```text
src/tracker/
├── App.java
├── Person.java
├── Student.java
├── Task.java
├── TaskManager.java
├── FileStore.java
├── ReportGenerator.java
└── ValidationTest.java

run.sh
test.sh
README.md
statement.md
```

### Class Overview

**App.java**  
Contains the main menu, user input handling, and demo mode.

**Person.java**  
Base class containing the common name field.

**Student.java**  
Extends `Person` and stores student-specific details.

**Task.java**  
Represents an assignment/task, its due date, and completion status.

**TaskManager.java**  
Contains the main student and task operations and validation rules.

**FileStore.java**  
Handles reading and writing the local CSV data files.

**ReportGenerator.java**  
Creates the task report and uses an executor for the report-generation step.

**ValidationTest.java**  
Runs a few basic checks for invalid input and missing students.

## Requirements

- Java JDK 17 or later
- Terminal / command-line environment
- No external libraries are required

## How to Run

From the project directory:

```bash
./run.sh
```

If the script does not have execute permission, use:

```bash
bash run.sh
```

### Demo Mode

To quickly check the main features:

```bash
./run.sh demo
```

or:

```bash
bash run.sh demo
```

### Validation Tests

```bash
./test.sh
```

or:

```bash
bash test.sh
```

## Data and Output

The application stores its data in simple CSV files inside the `data/` folder.

The generated report is written to:

```text
out/task-report.csv
```

No database server or GUI setup is needed.

## Why I Kept It Simple

This project is meant to be a small practical application rather than a full task-management platform. The main focus is on understanding how the Java concepts from the course fit together in one working program.

Keeping the application command-line based also makes it easier to run, test, and demonstrate on another computer.

## Future Improvements

A larger version could add login and user roles, better filtering, reminder notifications, or database support. Those features are outside the current scope so the project stays focused on the core Java topics.

## Author

**Yash Rao Patankar**  
Integrated M.Tech – Computational and Data Science  
VIT Bhopal University
