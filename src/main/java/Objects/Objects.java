package Objects;

public class Objects {
    // Basic Thing type that defines all objects in the Adventure

    private String name;
    private String description;

    public Objects(String Name, String Description) {
        // constructor
        this.name = Name;
        this.description = Description;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
