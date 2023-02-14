package model.patient;

import java.time.LocalDateTime;

public class Patient {
    private String name="";
    // gender: true for male, false for female
    private Boolean gender=false;
    private LocalDateTime sessionTime =LocalDateTime.now();
    public Patient() {}

    public Patient(String name, Boolean gender) {
        this.name = name;
        this.gender = gender;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public LocalDateTime getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(LocalDateTime sessionTime) {
        this.sessionTime = sessionTime;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", gender=" + (gender?"male":"female") +
                '}';
    }
}
