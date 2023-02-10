package model.doctor;

public class Disease {
    private String name;
    private int cureTime;

    public Disease() {
    }

    public Disease(String name, int cureTime) {
        this.name = name;
        this.cureTime = cureTime;
    }

    public int getCureTime() {
        return cureTime;
    }

    public void setCureTime(int cureTime) {
        this.cureTime = cureTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
