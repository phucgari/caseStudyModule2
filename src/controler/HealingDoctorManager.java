package controler;

import inputOutPut.Serializer;
import model.doctor.*;

import java.util.ArrayList;
import java.util.List;

// design Pattern singleton
public class HealingDoctorManager {
    private static HealingDoctorManager instance;
    private List<HealingDoctor> healingDoctorList;
    public static Serializer<HealingDoctor> serializer=new Serializer<>("src/data/healingDoctorList.dat");
    private HealingDoctorManager(){
        healingDoctorList=serializer.readObjects();
    }

    public List<HealingDoctor> getHealingDoctorList() {
        return healingDoctorList;
    }
    public void serializeList(){
        serializer.writeObjects(healingDoctorList);
    }
    public void flushHealingDoctorManager(){
        HealingDoctor doc1=new Dentist("Dentist1",2);
        HealingDoctor doc2=new Dentist("Dentist2", 3);
        HealingDoctor doc3=new Gynecologist("Gynecologist1", 4);
        HealingDoctor doc4=new Gynecologist("Gynecologist2",1);
        HealingDoctor doc5=new Otolaryngologist("Otolaryngologist1",2);
        HealingDoctor doc6=new Otolaryngologist("Otolaryngologist2",2);
        HealingDoctor doc7=new Urologist("Urologist1",1);
        HealingDoctor doc8=new Urologist("Urologist2",3);
        HealingDoctor doc9=new Surgeon("Surgeon1",3);
        HealingDoctor doc10=new Surgeon("Surgeon2",4);
        List<HealingDoctor> list=new ArrayList<>();
        list.add(doc1);
        list.add(doc2);
        list.add(doc3);
        list.add(doc4);
        list.add(doc5);
        list.add(doc6);
        list.add(doc7);
        list.add(doc8);
        list.add(doc9);
        list.add(doc10);
        Serializer<HealingDoctor>serializer=new Serializer<>("src/data/healingDoctorList.dat");
        serializer.writeObjects(list);
        healingDoctorList=list;
    }

    public static HealingDoctorManager getInstance(){
        if(instance==null)instance=new HealingDoctorManager();
        return instance;
    }

    public void addDentist(String name, int exp) {
        healingDoctorList.add(new Dentist(name,exp));
        serializer.writeObjects(healingDoctorList);
    }

    public void addGynecologist(String name, int exp) {
        healingDoctorList.add(new Gynecologist(name,exp));
        serializer.writeObjects(healingDoctorList);
    }

    public void addUrologist(String name, int exp) {
        healingDoctorList.add(new Urologist(name,exp));
        serializer.writeObjects(healingDoctorList);
    }

    public void addOtolaryngologist(String name, int exp) {
        healingDoctorList.add(new Otolaryngologist(name,exp));
        serializer.writeObjects(healingDoctorList);
    }

    public void addSurgeon(String name, int exp) {
        healingDoctorList.add(new Surgeon(name,exp));
        serializer.writeObjects(healingDoctorList);
    }

    public void remove(int i) {
        healingDoctorList.remove(i);
        serializer.writeObjects(healingDoctorList);
    }
}
