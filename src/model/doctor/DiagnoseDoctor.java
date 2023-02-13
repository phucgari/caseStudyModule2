package model.doctor;

public class DiagnoseDoctor extends Doctor{
    public DiagnoseDoctor(String name, int experienceIndex) {
        super(name,experienceIndex);
    }

    public DiagnoseDoctor() {}

    @Override
    public String toString() {
        return "DiagnoseDoctor "+super.toString();
    }
}
