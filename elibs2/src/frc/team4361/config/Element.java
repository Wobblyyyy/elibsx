package frc.team4361.config;

public class Element {
    private String name = "";
    private double id = 0;

    public Element() {

    }

    public Element(String name,
                   int id) {
        this.name = name;
        this.id = id;
    }

    public Element(Common common,
                   int id) {
        this.name = common.getName();
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getId() {
        return id;
    }
}
