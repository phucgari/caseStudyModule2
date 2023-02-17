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

    public void setHealingDoctorList(List<HealingDoctor> healingDoctorList) {
        this.healingDoctorList = healingDoctorList;
    }
    public void flushHealingDoctorManager(){
        HealingDoctor doc1=new Dentist("Dentist1",2);
        HealingDoctor doc2=new Dentist("Dentist2", 3);
        HealingDoctor doc3=new Gynecologist("Gynecologist1", 4);
        HealingDoctor doc4=new Gynecologist("Gynecologist2",1);
        HealingDoctor doc5=new Otolaryngologist("Otolaryngologist1",2);
        HealingDoctor doc6=new Otolaryngologist("Otolaryngologist2",2);
        HealingDoctor doc7=new Urologists("Urologist1",1);
        HealingDoctor doc8=new Urologists("Urologist2",3);
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
}
