package model.doctor;

import model.patient.Patient;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Doctor implements Serializable {
    private String name="";
    private Experience experience=new Experience();
    private Queue<Patient>patientQueue=new LinkedList<>();

    public Doctor() {}

    public Doctor(String name, int experienceIndex) {
        this.name = name;
        this.experience = new Experience(experienceIndex);
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Queue<Patient> getPatientQueue() {
        return patientQueue;
    }

    public void setPatientQueue(Queue<Patient> patientQueue) {
        this.patientQueue = patientQueue;
    }
    abstract public void takePatient(Patient patient,Disease disease);

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
