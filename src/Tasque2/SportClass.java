package Tasque2;

public class SportClass {
    private int id;
    private String name;

    public SportClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SportClass(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
