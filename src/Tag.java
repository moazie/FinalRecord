public class Tag {
    private String name;

    public Tag(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "General";
        } 
        else {
            this.name = name.trim();
        }
    }
    
    public String getTag() {
        return name;
    }

    @Override
    public String toString() {
        return "#" + name;
    }
}
