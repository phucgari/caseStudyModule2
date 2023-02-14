package model.doctor;

import inputOutPut.Serializer;

public class Urologists extends HealingDoctor {
    public static final Serializer<Disease> DISEASE_SERIALIZER =new Serializer<>("src/data/disease/urologistsDisease.dat");

    public Urologists() {
        setCurableDisease(DISEASE_SERIALIZER.readObjects());
    }

    public Urologists(String name, int experience) {
        super(name, experience, DISEASE_SERIALIZER.readObjects());
    }

    @Override
    public String toString() {
        return "Urologists " + super.toString();
    }
}
