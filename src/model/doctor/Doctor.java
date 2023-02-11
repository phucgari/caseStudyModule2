package model.doctor;

import model.patient.Patient;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Doctor implements Serializable {
    private String name="";
    private Experience experience=new Experience();
    private Queue<Patient>patientQueue=new LinkedList<>();
    private List<Disease> curableDisease;
    public Doctor() {}

    public Doctor(String name, int experience,List<Disease> curableDisease) {
        this.name = name;
        this.experience.setExperience(experience);
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

    public void setPatientQueue(Queue<Patient> patientQueue) {
        this.patientQueue = patientQueue;
    }
    public void takePatient(Patient patient, int index) {
        getPatientQueue().add(patient);
        Disease disease=getCurableDisease().get(index);
        double trueCureTime = disease.getCureTime()/getTimeMultiplier();
        LocalDateTime newSessionTime = patient.getSessionTime().plusSeconds((long) trueCureTime);
        patient.setSessionTime(newSessionTime);
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
        return  "name='" + name + '\''
                + experience +"\n";
    }
}
