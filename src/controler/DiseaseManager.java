package controler;

import inputOutPut.Serializer;
import model.doctor.*;

import java.util.ArrayList;
import java.util.List;

public class DiseaseManager {
    public static Serializer<Disease> serializer=new Serializer<>("src/data/disease/diseaseList.dat");
    private static DiseaseManager instance;
    private List<Disease> list=new ArrayList<>();
    private DiseaseManager(){
        List<Disease> dentistDisease = Dentist.DISEASE_SERIALIZER.readObjects();
        List<Disease> gynecologistDisease= Gynecologist.DISEASE_SERIALIZER.readObjects();
        List<Disease> otolaryngologistDisease= Otolaryngologist.DISEASE_SERIALIZER.readObjects();
        List<Disease> urologistDisease= Urologists.DISEASE_SERIALIZER.readObjects();
        List<Disease> surgeonDisease=Surgeon.DISEASE_SERIALIZER.readObjects();
        list.addAll(dentistDisease);
        list.addAll(gynecologistDisease);
        list.addAll(otolaryngologistDisease);
        list.addAll(urologistDisease);
        list.addAll(surgeonDisease);
        serializer.writeObjects(list);
    }
    public static DiseaseManager getInstance() {
        if(instance==null)instance=new DiseaseManager();
        return instance;
    }

    public List<Disease> getList() {
        return list;
    }
}
