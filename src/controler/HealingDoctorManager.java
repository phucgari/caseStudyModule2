package controler;

import inputOutPut.Serializer;
import model.doctor.HealingDoctor;

import java.util.List;

// design Pattern singleton
public class HealingDoctorManager {
    private static HealingDoctorManager instance;
    private List<HealingDoctor> healingDoctorList;
    private HealingDoctorManager(){
        Serializer<HealingDoctor> serializer=new Serializer<>("src/data/healingDoctorList.dat");
        healingDoctorList=serializer.readObjects();
    }

    public List<HealingDoctor> getHealingDoctorList() {
        return healingDoctorList;
    }

    public static HealingDoctorManager getInstance(){
        if(instance==null)instance=new HealingDoctorManager();
        return instance;
    }
}
