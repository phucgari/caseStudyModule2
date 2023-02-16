package controler;

import inputOutPut.Serializer;
import model.doctor.HealingDoctor;

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
        return serializer.readObjects();
    }
    public void serializeList(){
        serializer.writeObjects(healingDoctorList);
    }

    public void setHealingDoctorList(List<HealingDoctor> healingDoctorList) {
        this.healingDoctorList = healingDoctorList;
    }

    public static HealingDoctorManager getInstance(){
        if(instance==null)instance=new HealingDoctorManager();
        return instance;
    }
}
