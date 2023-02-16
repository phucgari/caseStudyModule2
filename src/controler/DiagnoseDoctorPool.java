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
    private static final String linkToDiagnoseDoctorListAndQueue="src/data/diagnoseDoctorLists";
    public static final QueueSerializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_LIST =new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorList.dat");
    public static final QueueSerializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_AVAILABLE =new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorAvailable.dat");
    public static final QueueSerializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_INUSE =new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorInuse.dat");
    private PriorityQueue<DiagnoseDoctor> available= DIAGNOSE_DOCTOR_AVAILABLE.readObjects();
    private PriorityQueue<DiagnoseDoctor> inuse= DIAGNOSE_DOCTOR_INUSE.readObjects();
    public PriorityQueue<DiagnoseDoctor> getAvailable() {
        available= DIAGNOSE_DOCTOR_AVAILABLE.readObjects();
        return available;
    }
    public PriorityQueue<DiagnoseDoctor> getInuse() {
        inuse=DIAGNOSE_DOCTOR_INUSE.readObjects();
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

        DIAGNOSE_DOCTOR_AVAILABLE.writeObjects(available);
        DIAGNOSE_DOCTOR_INUSE.writeObjects(inuse);
    }
    public void releasePatient(){
//        throw exception if no inuse Doctor
        boolean boo=inuse.isEmpty();
        if(inuse.isEmpty())throw new RuntimeException("No Inuse Doctor");
        DiagnoseDoctor doctor=inuse.remove();
        Patient patient=doctor.getCurrent();
        Disease disease=randomDisease();

        patient.setDisease(disease);
        doctor.setCurrent(new Patient("prevent null",true));
        available.add(doctor);

//        transferDocFromInUseToAvailable
//        change Patient Disease
//        change Patient sessionTime
//        change diagnoseDoc current to null
        DIAGNOSE_DOCTOR_AVAILABLE.writeObjects(available);
        DIAGNOSE_DOCTOR_INUSE.writeObjects(inuse);
        HealingDoctor healingDoctorChosen=HospitalManager.getInstance().giveDiseaseGetHealingDoc(disease);
        healingDoctorChosen.takePatient(patient);

//        chose HealingDoc to push
//        then push Patient to HealingDocQueue
//        Serialize
    }

    private Disease randomDisease() {
        Disease result;
        result = getRandomDisease();
        return result;
    }

    private static Disease getRandomDisease() {
        Disease result;
        Random random = new Random();
        int rand = 0;
        List<Disease>diseaseList=DiseaseManager.getInstance().getList();
        rand = random.nextInt(diseaseList.size()+3);
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
