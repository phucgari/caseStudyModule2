package model.doctor;

public class Experience {
    private int experience=-1;

    public Experience() {}

    Experience(int experiencePoint){
        experience=experiencePoint;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getTimeMultiplier(){
    switch (experience){
        case 1:
            return 1;
        case 2:
            return 1.25;
        case 3:
            return 1.5;
        case 4:
            return 2;
    }
    throw new RuntimeException("Invalid experienceIndex");
    }

    @Override
    public String toString() {
        switch (experience){
            case 1:
                return "Experience: Fresher";
            case 2:
                return "Experience: Junior";
            case 3:
                return "Experience: Senior";
            case 4:
                return "Experience: Leader";
        }
        throw new RuntimeException("Invalid experienceIndex");
    }
}
