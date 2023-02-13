package model.doctor;

import model.patient.Patient;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class HealingDoctor extends Doctor {
    private Queue<Patient>patientQueue=new LinkedList<>();
    private List<Disease> curableDisease;
    public HealingDoctor() {}

    public HealingDoctor(String name, int experience, List<Disease> curableDisease) {
        super(name,experience);
        this.curableDisease=curableDisease;
    }

    public List<Disease> getCurableDisease() {
        return curableDisease;
    }

    public void setCurableDisease(List<Disease> curableDisease) {
        this.curableDisease = curableDisease;
    }

    public Queue<Patient> getPatientQueue() {
        return patientQueue;
    }

    public void takePatient(Patient patient, int index) {
        Disease disease=getCurableDisease().get(index);
        double trueCureTime = disease.getCureTime()/getTimeMultiplier();
        LocalDateTime newSessionTime;
        if(patientQueue.isEmpty())
        {
            newSessionTime = patient.getSessionTime().plusSeconds((long) trueCureTime);
        }else {
            newSessionTime = patientQueue.peek().getSessionTime().plusSeconds((long) trueCureTime);
        }
        patient.setSessionTime(newSessionTime);
        getPatientQueue().add(patient);
    }
}
