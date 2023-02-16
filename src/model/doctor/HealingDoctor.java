package model.doctor;

import model.patient.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;

public abstract class HealingDoctor extends Doctor implements Comparable<HealingDoctor>{
    private PriorityQueue<Patient>patientQueue=new PriorityQueue<>();
    private List<Disease> curableDisease;
    private LocalDateTime lastPatientTimer;

    public LocalDateTime getLastPatientTimer() {
        return lastPatientTimer;
    }

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

    public PriorityQueue<Patient> getPatientQueue() {
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
        lastPatientTimer=newSessionTime;
        getPatientQueue().add(patient);
    }

    @Override
    public int compareTo(HealingDoctor o) {

        return patientQueue.peek().compareTo(o.patientQueue.peek());
    }
}
