package model.doctor;

import inputOutPut.Serializer;

public class Surgeon extends HealingDoctor {

    public final static Serializer<Disease> DISEASE_SERIALIZER =new Serializer<>("src/data/disease/surgeonDisease.dat");

    public Surgeon() {
        setCurableDisease(DISEASE_SERIALIZER.readObjects());
    }

    public Surgeon(String name, int experienceIndex) {
        super(name, experienceIndex, DISEASE_SERIALIZER.readObjects());
    }

    @Override
    public String toString() {
        return "Surgeon " + super.toString();
    }
}
