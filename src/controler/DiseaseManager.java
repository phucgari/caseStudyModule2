package controler;

import inputOutPut.Serializer;
import model.doctor.*;

import java.util.ArrayList;
import java.util.List;

public class DiseaseManager {
    private static DiseaseManager instance;
    public static Serializer<Disease> serializer=new Serializer<>("src/data/disease/diseaseList.dat");
    private final List<Disease> dentistDisease = Dentist.DISEASE_SERIALIZER.readObjects();
    private final List<Disease> gynecologistDisease= Gynecologist.DISEASE_SERIALIZER.readObjects();
    private final List<Disease> otolaryngologistDisease= Otolaryngologist.DISEASE_SERIALIZER.readObjects();
    private final List<Disease> urologistDisease= Urologist.DISEASE_SERIALIZER.readObjects();
    private final List<Disease> surgeonDisease=Surgeon.DISEASE_SERIALIZER.readObjects();
    private final List<Disease> list=new ArrayList<>();
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

    public void addDentistDisease(String name, int cureTime) {
        dentistDisease.add(new Disease(name,cureTime));
        list.add(new Disease(name,cureTime));
        Dentist.DISEASE_SERIALIZER.writeObjects(dentistDisease);
    }
    public void addGynecologistDisease(String name, int cureTime) {
        gynecologistDisease.add(new Disease(name,cureTime));
        list.add(new Disease(name,cureTime));
        Gynecologist.DISEASE_SERIALIZER.writeObjects(gynecologistDisease);
    }

    public void addUrologistDisease(String name, int cureTime) {
        urologistDisease.add(new Disease(name,cureTime));
        list.add(new Disease(name,cureTime));
        Urologist.DISEASE_SERIALIZER.writeObjects(urologistDisease);
    }

    public void addOtolaryngologistDisease(String name, int cureTime) {
        otolaryngologistDisease.add(new Disease(name,cureTime));
        list.add(new Disease(name,cureTime));
        Otolaryngologist.DISEASE_SERIALIZER.writeObjects(otolaryngologistDisease);
    }

    public void addSurgeonDisease(String name, int cureTime) {
        surgeonDisease.add(new Disease(name,cureTime));
        list.add(new Disease(name,cureTime));
        Surgeon.DISEASE_SERIALIZER.writeObjects(surgeonDisease);
    }
}
