package Tasque2;

public class AthleteClass {
    private int id;
    private String name;
    private int sportId;

    public AthleteClass(int id, String name, int sportId) {
        this.id = id;
        this.name = name;
        this.sportId = sportId;
    }

    public AthleteClass(String name, int sportId) {
        this.name = name;
        this.sportId = sportId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSportId() {
        return sportId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }
}
