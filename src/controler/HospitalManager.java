package controler;

import model.doctor.*;
import model.patient.Patient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.PriorityQueue;

public class HospitalManager {
    private static HospitalManager instance;

    private String newLine = System.getProperty("line.separator");
    private HospitalManager() {}
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static HospitalManager getInstance() {
        if(instance==null)instance=new HospitalManager();
        return instance;
    }
    public void checkPatientInHealer(){
        LocalDateTime now=LocalDateTime.now();
        List<HealingDoctor> healingDoctorList=HealingDoctorManager.getInstance().getHealingDoctorList();
        for (HealingDoctor healer :
                healingDoctorList) {
            PriorityQueue<Patient> queue = healer.getPatientQueue();
            while (!queue.isEmpty()) {
                LocalDateTime patientSessionTime = queue.peek().getSessionTime();
                if (patientSessionTime.isBefore(now)) {
                    Patient patient = queue.remove();
                    System.out.printf("%s: %-40s go from %-60s with %-50s to %-60s with %-50s newSessionTime %-50s"+newLine, LocalDateTime.now().format(formatter),patient,healer,patient.getDisease(),"out of hospital","No Disease",patient.getSessionTime().format(formatter));
                    HealingDoctorManager.getInstance().serializeList();
                }else break;
            }
        }
    }
    public void flushAll(){
        DiagnoseDoctorPool.getInstance().flushAvailableInuse();
        PatientManager.getInstance().emptyPatientQueue();
        HealingDoctorManager.getInstance().flushHealingDoctorManager();
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
            if(kindOfDocIndex!=-1)break;
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
