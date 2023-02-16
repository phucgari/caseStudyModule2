package controler;

import model.doctor.*;

import java.time.LocalDateTime;
import java.util.List;

public class HospitalManager {
    private static HospitalManager instance;

    private HospitalManager() {}

    public static HospitalManager getInstance() {
        if(instance==null)instance=new HospitalManager();
        return instance;
    }

    public HealingDoctor giveDiseaseGetHealingDoc(Disease disease) {
        //        chose HealingDoc to push
        List<Disease>[]lists=DiseaseManager.getInstance().DiseaseListArray();
        int kindOfDocIndex=-1;
        for (int i = 0; i < lists.length; i++) {
            for (int j = 0; j < lists[i].size(); j++) {
                if (disease.getName().equals(lists[i].get(j).getName())){
                    kindOfDocIndex=i;
                    break;
                }
            }
        }
        ;
        return getDocFromDocIndex(kindOfDocIndex) ;
    }

    private HealingDoctor getDocFromDocIndex(int kindOfDocIndex) {
        HealingDoctor doc=null;
        List<HealingDoctor>healingDoctorList=HealingDoctorManager.getInstance().getHealingDoctorList();
        switch (kindOfDocIndex){
            case 0:
                doc= checkDentistIfEmpty(healingDoctorList);
                if(doc==null)doc= checkDentistWithLowestTime(healingDoctorList);
                break;
            case 1:
                doc= checkGynecologistIfEmpty(healingDoctorList);
                if(doc==null)doc= checkGynecologistWithLowestTime(healingDoctorList);
                break;
            case 2:
                doc= checkOtolaryngologistIfEmpty(healingDoctorList);
                if(doc==null)doc= checkOtolaryngologistWithLowestTime(healingDoctorList);
                break;
            case 3:
                doc= checkUrologistsIfEmpty(healingDoctorList);
                if(doc==null)doc=checkUrologistsWithLowestTime(healingDoctorList);
                break;
            case 4:
                doc= checkSurgeonIfEmpty(healingDoctorList);
                if(doc==null)doc=checkSurgeonWithLowestTime(healingDoctorList);
                break;
        }
        return doc;
    }
    private HealingDoctor checkSurgeonWithLowestTime(List<HealingDoctor> healingDoctorList) {
        LocalDateTime time=null;
        HealingDoctor result=null;
        for (HealingDoctor doctor :
                healingDoctorList) {
            if (doctor instanceof Surgeon){
                if(time==null){
                    time=doctor.getLastPatientTimer();
                    result=doctor;
                }
                if(doctor.getLastPatientTimer().isBefore(time)){
                    time=doctor.getLastPatientTimer();
                    result=doctor;
                }
            }
        }
        return result;
    }
    private HealingDoctor checkUrologistsWithLowestTime(List<HealingDoctor> healingDoctorList) {
        LocalDateTime time=null;
        HealingDoctor result=null;
        for (HealingDoctor doctor :
                healingDoctorList) {
            if (doctor instanceof Urologists){
                if(time==null){
                    time=doctor.getLastPatientTimer();
                    result=doctor;
                }
                if(doctor.getLastPatientTimer().isBefore(time)){
                    time=doctor.getLastPatientTimer();
                    result=doctor;
                }
            }
        }
        return result;
    }
    private HealingDoctor checkOtolaryngologistWithLowestTime(List<HealingDoctor> healingDoctorList) {
        LocalDateTime time=null;
        HealingDoctor result=null;
        for (HealingDoctor doctor :
                healingDoctorList) {
            if (doctor instanceof Otolaryngologist){
                if(time==null){
                    time=doctor.getLastPatientTimer();
                    result=doctor;
                }
                if(doctor.getLastPatientTimer().isBefore(time)){
                    time=doctor.getLastPatientTimer();
                    result=doctor;
                }
            }
        }
        return result;
    }
    private HealingDoctor checkGynecologistWithLowestTime(List<HealingDoctor> healingDoctorList) {
        LocalDateTime time=null;
        HealingDoctor result=null;
        for (HealingDoctor doctor :
                healingDoctorList) {
            if (doctor instanceof Gynecologist){
                if(time==null){
                    time=doctor.getLastPatientTimer();
                    result=doctor;
                }
                if(doctor.getLastPatientTimer().isBefore(time)){
                    time=doctor.getLastPatientTimer();
                    result=doctor;
                }
            }
        }
        return result;
    }
    private HealingDoctor checkDentistWithLowestTime(List<HealingDoctor> healingDoctorList) {
        LocalDateTime time=null;
        HealingDoctor result=null;
        for (HealingDoctor doctor :
                healingDoctorList) {
            if (doctor instanceof Dentist){
                if(time==null){
                    time=doctor.getLastPatientTimer();
                    result=doctor;
                }
                if(doctor.getLastPatientTimer().isBefore(time)){
                    time=doctor.getLastPatientTimer();
                    result=doctor;
                }
            }
        }
        return result;
    }
    private HealingDoctor checkSurgeonIfEmpty(List<HealingDoctor> healingDoctorList) {
        for (HealingDoctor doctor :
                healingDoctorList) {
            if(doctor instanceof Surgeon){
                if(doctor.getPatientQueue().isEmpty())return doctor;
            }
        }
        return null;
    }
    private HealingDoctor checkUrologistsIfEmpty(List<HealingDoctor> healingDoctorList) {
        for (HealingDoctor doctor :
                healingDoctorList) {
            if(doctor instanceof Urologists){
                if(doctor.getPatientQueue().isEmpty())return doctor;
            }
        }
        return null;
    }
    private HealingDoctor checkOtolaryngologistIfEmpty(List<HealingDoctor> healingDoctorList) {
        for (HealingDoctor doctor :
                healingDoctorList) {
            if(doctor instanceof Otolaryngologist){
                if(doctor.getPatientQueue().isEmpty())return doctor;
            }
        }
        return null;
    }
    private HealingDoctor checkGynecologistIfEmpty(List<HealingDoctor> healingDoctorList) {
        for (HealingDoctor doctor :
                healingDoctorList) {
            if(doctor instanceof Gynecologist){
                if(doctor.getPatientQueue().isEmpty())return doctor;
            }
        }
        return null;
    }
    private HealingDoctor checkDentistIfEmpty(List<HealingDoctor> healingDoctorList) {
        for (HealingDoctor doctor :
                healingDoctorList) {
            if(doctor instanceof Dentist){
                if(doctor.getPatientQueue().isEmpty())return doctor;
            }
        }
        return null;
    }
}
