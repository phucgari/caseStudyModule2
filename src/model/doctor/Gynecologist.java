package model.doctor;

import inputOutPut.Serializer;

public class Gynecologist extends HealingDoctor {
    public static final Serializer<Disease> DISEASE_SERIALIZER =new Serializer<>("src/data/disease/gynecologistDisease.dat");

    public Gynecologist() {
        setCurableDisease(DISEASE_SERIALIZER.readObjects());
    }

    public Gynecologist(String name, int experience) {
        super(name,experience, DISEASE_SERIALIZER.readObjects());
    }

    @Override
    public String toString() {
        return "Gynecologist " + super.toString();
    }
}
