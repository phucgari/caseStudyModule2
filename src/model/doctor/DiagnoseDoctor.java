package model.doctor;

import model.patient.Patient;

public class DiagnoseDoctor extends Doctor implements Comparable<DiagnoseDoctor>{
    private Patient current=new Patient("prevent null",true);
    public DiagnoseDoctor(String name, int experienceIndex) {
        super(name,experienceIndex);
        current.setSessionTime(current.getSessionTime().plusSeconds((long) (100/getTimeMultiplier())));
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

    @Override
    public int compareTo(DiagnoseDoctor o) {
        return current.compareTo(o.getCurrent());
    }
}
