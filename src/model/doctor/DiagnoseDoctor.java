package model.doctor;

import model.patient.Patient;

public class DiagnoseDoctor extends Doctor{
    private Patient current;
    public DiagnoseDoctor(String name, int experienceIndex) {
        super(name,experienceIndex);
    }

    public DiagnoseDoctor() {}

    @Override
    public String toString() {
        return "DiagnoseDoctor "+super.toString();
    }

    public Patient getCurrent() {
        return current;
    }

    public void setCurrent(Patient current) {
        this.current = current;
    }
}
