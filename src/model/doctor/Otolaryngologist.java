package model.doctor;

import inputOutPut.Serializer;

public class Otolaryngologist extends HealingDoctor {
    public final static Serializer<Disease> DISEASE_SERIALIZER =new Serializer<>("src/data/otolaryngologistDisease.dat");

    public Otolaryngologist() {
        setCurableDisease(DISEASE_SERIALIZER.readObjects());
    }

    public Otolaryngologist(String name, int experienceIndex) {
        super(name, experienceIndex, DISEASE_SERIALIZER.readObjects());
    }

    @Override
    public String toString() {
        return "Otolaryngologist " + super.toString();
    }
}
