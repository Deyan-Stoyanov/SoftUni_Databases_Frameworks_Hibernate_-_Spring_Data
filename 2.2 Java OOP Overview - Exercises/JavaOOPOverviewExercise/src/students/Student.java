package students;

public class Student {
    private String name;
    public static int count = 0;

    public Student(String name) {
        this.name = name;
        count = ++count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }
}
