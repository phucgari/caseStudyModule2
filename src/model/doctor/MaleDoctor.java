package model.doctor;

import inputOutPut.Serializer;

public class MaleDoctor extends Doctor {
    public static final Serializer<Disease> DISEASE_SERIALIZER =new Serializer<>("src/data/maleDoctorDisease.dat");

    public MaleDoctor() {
        setCurableDisease(DISEASE_SERIALIZER.readObjects());
    }

    public MaleDoctor(String name, int experience) {
        super(name, experience, DISEASE_SERIALIZER.readObjects());
    }

    @Override
    public String toString() {
        return "MaleDoctor " + super.toString();
    }
}
