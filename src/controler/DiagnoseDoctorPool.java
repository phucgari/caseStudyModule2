package controler;

import inputOutPut.QueueSerializer;
import model.doctor.DiagnoseDoctor;
import model.doctor.Disease;
import model.doctor.HealingDoctor;
import model.patient.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

// design pattern Object pool
public class DiagnoseDoctorPool {
    static private DiagnoseDoctorPool instance;
    private static final String linkToDiagnoseDoctorListAndQueue="src/data/diagnoseDoctorLists";
    public static final QueueSerializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_LIST =new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorList.dat");
    public static final QueueSerializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_AVAILABLE =new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorAvailable.dat");
    public static final QueueSerializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_INUSE =new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorInuse.dat");
    private PriorityQueue<DiagnoseDoctor> available= DIAGNOSE_DOCTOR_AVAILABLE.readObjects();
    private PriorityQueue<DiagnoseDoctor> inuse= DIAGNOSE_DOCTOR_INUSE.readObjects();

    private DiagnoseDoctorPool(){}

    public static DiagnoseDoctorPool getInstance() {
        if (instance==null)instance=new DiagnoseDoctorPool();
        return instance;
    }

    public PriorityQueue<DiagnoseDoctor> getAvailable() {
        return available;
    }
    public PriorityQueue<DiagnoseDoctor> getInuse() {
        return inuse;
    }
    public void getPatient() {
        if(PatientManager.getInstance().getPatientQueue().isEmpty())throw new RuntimeException("No Patient in Queue");
        if(getAvailable().isEmpty())throw new RuntimeException("No available DiagnoseDoctor");

        DiagnoseDoctor doctor= getAvailable().remove();
        Patient patient=PatientManager.getInstance().removePatientQueue();

        long sessionAddTime = (long) (15 / doctor.getTimeMultiplier());
        LocalDateTime newSessionTime=LocalDateTime.now().plusSeconds(sessionAddTime);
        patient.setSessionTime(newSessionTime);

        doctor.setCurrent(patient);
        getInuse().add(doctor);
    }
    public boolean releasePatient(){
//        throw exception if no inuse Doctor
        if(inuse.isEmpty())throw new RuntimeException("No Inuse Doctor");
        DiagnoseDoctor doctor=inuse.remove();
        Patient patient=doctor.getCurrent();
        Disease disease=randomDisease(patient.getGender());
        patient.setDisease(disease);
        doctor.setCurrent(new Patient("prevent null",true));
        available.add(doctor);
//        transferDocFromInUseToAvailable
//        change Patient Disease
//        change Patient sessionTime
//        change diagnoseDoc current to null
        if (disease.getName().equals("No Disease"))return false;
        HospitalManager hospitalManager = HospitalManager.getInstance();

        HealingDoctor healingDoctorChosen= hospitalManager.giveDiseaseGetHealingDoc(disease);
        healingDoctorChosen.takePatient(patient);
        return true;
//        chose HealingDoc to push
//        then push Patient to HealingDocQueue
//        Serialize
    }
    public void saveAvailableInuse(){
        DIAGNOSE_DOCTOR_AVAILABLE.writeObjects(available);
        DIAGNOSE_DOCTOR_INUSE.writeObjects(inuse);
    }
    private Disease randomDisease(Boolean gender) {
        if(gender){
            List<Disease>maleList=DiseaseManager.getInstance().getMaleList();
            return getRandomDisease(maleList);
        }else {
            List<Disease>femaleList=DiseaseManager.getInstance().getFemaleList();
            return getRandomDisease(femaleList);
        }
    }
    private static Disease getRandomDisease(List<Disease> diseaseList) {
        Disease result;
        Random random = new Random();
        int rand = 0;
        rand = random.nextInt(diseaseList.size()+2);
        try {
            result = diseaseList.get(rand);
        }catch (IndexOutOfBoundsException e){
            result= new Disease("No Disease",0);
        }
        return result;
    }

    public void flushAvailableInuse(){
        DIAGNOSE_DOCTOR_AVAILABLE.writeObjects(DIAGNOSE_DOCTOR_LIST.readObjects());
        DIAGNOSE_DOCTOR_INUSE.writeObjects(new PriorityQueue<>());
        inuse=new PriorityQueue<>();
        available=DIAGNOSE_DOCTOR_AVAILABLE.readObjects();
    }
}
