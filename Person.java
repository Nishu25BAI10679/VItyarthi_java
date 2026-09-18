package tracker;

public class Person {
    private final String name;

    public Person(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }
}
