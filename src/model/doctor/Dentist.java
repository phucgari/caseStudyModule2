package model.doctor;

import inputOutPut.Serializer;

public class Dentist extends HealingDoctor {
    public final static Serializer<Disease> DISEASE_SERIALIZER =new Serializer<>("src/data/disease/dentistDisease.dat");

    public Dentist() {
        setCurableDisease(DISEASE_SERIALIZER.readObjects());
    }

    public Dentist(String name, int experienceIndex) {
        super(name, experienceIndex, DISEASE_SERIALIZER.readObjects());
    }

    @Override
    public String toString() {
        return "Dentist " + super.toString();
    }
}
