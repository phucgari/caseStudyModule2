package model.doctor;

import inputOutPut.Serializer;

public class Urologist extends HealingDoctor {
    public static final Serializer<Disease> DISEASE_SERIALIZER =new Serializer<>("src/data/disease/urologistsDisease.dat");

    public Urologist() {
        setCurableDisease(DISEASE_SERIALIZER.readObjects());
    }

    public Urologist(String name, int experience) {
        super(name, experience, DISEASE_SERIALIZER.readObjects());
    }

    @Override
    public String toString() {
        return "Urologist " + super.toString();
    }
}
