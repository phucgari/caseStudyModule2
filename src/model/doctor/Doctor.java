package model.doctor;

import java.io.Serializable;

public abstract class Doctor implements Serializable {
    private String name="";
    private Experience experience=new Experience();

    public Doctor() {}

    public Doctor(String name, int experienceIndex) {
        this.name = name;
        this.experience = new Experience(experienceIndex);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience.getExperience();
    }

    public void setExperience(int experienceIndex) {
        this.experience.setExperience(experienceIndex);
    }
    public double getTimeMultiplier(){
        return experience.getTimeMultiplier();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\''
                + experience +
                '}';
    }
}
