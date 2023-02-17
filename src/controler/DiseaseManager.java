package controler;

import inputOutPut.Serializer;
import model.doctor.*;

import java.util.ArrayList;
import java.util.List;

public class DiseaseManager {
    private static DiseaseManager instance;
    public static Serializer<Disease> serializer=new Serializer<>("src/data/disease/diseaseList.dat");
    private List<Disease> dentistDisease = Dentist.DISEASE_SERIALIZER.readObjects();
    private List<Disease> gynecologistDisease= Gynecologist.DISEASE_SERIALIZER.readObjects();
    private List<Disease> otolaryngologistDisease= Otolaryngologist.DISEASE_SERIALIZER.readObjects();
    private List<Disease> urologistDisease= Urologists.DISEASE_SERIALIZER.readObjects();
    private List<Disease> surgeonDisease=Surgeon.DISEASE_SERIALIZER.readObjects();
    private List<Disease> list=new ArrayList<>();
    private DiseaseManager(){
        List<Disease>[] lists=DiseaseListArray();
        for (List<Disease>smallist:lists){
            list.addAll(smallist);
        }
        serializer.writeObjects(list);
    }

    public List<Disease>[] DiseaseListArray() {
        List<Disease>[] lists= new List[]{dentistDisease, gynecologistDisease, otolaryngologistDisease, urologistDisease, surgeonDisease};
        return lists;
    }

    public static DiseaseManager getInstance() {
        if(instance==null)instance=new DiseaseManager();
        return instance;
    }

    public List<Disease> getList() {
        return list;
    }
    public List<Disease> getMaleList(){
        List<Disease> result = new ArrayList<>(list);
        result.removeAll(gynecologistDisease);
        return result;
    }
    public List<Disease> getFemaleList(){
        List<Disease> result = new ArrayList<>(list);
        result.removeAll(urologistDisease);
        return result;
    }
}
