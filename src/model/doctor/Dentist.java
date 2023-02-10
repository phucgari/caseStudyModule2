package model.doctor;

import model.patient.Patient;

import java.time.LocalDateTime;

public class Dentist extends Doctor{
    public Dentist() {}

    public Dentist(String name, int experienceIndex) {
        super(name, experienceIndex);
    }

    @Override
    public void takePatient(Patient patient, Disease disease) {
        getPatientQueue().add(patient);
        double trueCureTime = disease.getCureTime()/getTimeMultiplier();
        LocalDateTime newSessionTime = patient.getSessionTime().plusSeconds((long) trueCureTime);
        patient.setSessionTime(newSessionTime);
    }

    @Override
    public String toString() {
        return "Dentist " + super.toString();
    }
}
