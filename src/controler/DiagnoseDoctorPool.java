package controler;

import inputOutPut.QueueSerializer;
import inputOutPut.Serializer;
import model.doctor.DiagnoseDoctor;
import model.doctor.Disease;
import model.doctor.HealingDoctor;
import model.patient.Patient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// design pattern Object pool
public class DiagnoseDoctorPool {
    static private DiagnoseDoctorPool instance;
    private static final String linkToDiagnoseDoctorListAndQueue="src/data/diagnoseDoctorLists";
    public static final Serializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_LIST =new Serializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorList.dat");
    private List<DiagnoseDoctor> list=new LinkedList<>(DIAGNOSE_DOCTOR_LIST.readObjects());
    public static final QueueSerializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_AVAILABLE =new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorAvailable.dat");
    public static final QueueSerializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_INUSE =new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorInuse.dat");
    private PriorityQueue<DiagnoseDoctor> available= DIAGNOSE_DOCTOR_AVAILABLE.readObjects();
    private PriorityQueue<DiagnoseDoctor> inuse= DIAGNOSE_DOCTOR_INUSE.readObjects();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String newLine = System.getProperty("line.separator");

    private DiagnoseDoctorPool(){}

    public static DiagnoseDoctorPool getInstance() {
        if (instance==null)instance=new DiagnoseDoctorPool();
        return instance;
    }

    public List<DiagnoseDoctor> getList() {
        return list;
    }
    public void removeDiagnoseDoctor(int index){
        list.remove(index);
        DIAGNOSE_DOCTOR_LIST.writeObjects(list);
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
        DiagnoseDoctor doctor=inuse.peek();
        Patient patient=doctor.getCurrent();
        Disease disease=randomDisease(patient.getGender());
        HospitalManager hospitalManager = HospitalManager.getInstance();

        HealingDoctor healingDoctorChosen= hospitalManager.giveDiseaseGetHealingDoc(disease);
        if (healingDoctorChosen==null&&!disease.getName().equals("No Disease")){
            return false;
        }
        inuse.remove();
        patient.setDisease(disease);
        doctor.setCurrent(new Patient("prevent null",true));
        for (DiagnoseDoctor diagnoseDoctor :
                list) {
            if(diagnoseDoctor.getName().equals(doctor.getName())){
                available.add(doctor);
            }
        }
//        transferDocFromInUseToAvailable
//        change Patient Disease
//        change Patient sessionTime
//        change diagnoseDoc current to null
        if (disease.getName().equals("No Disease")){
            System.out.printf("%s: %-40s go from %-60s with %-50s to %-60s with %-50s"+newLine, LocalDateTime.now().format(formatter),patient,doctor,"No Disease","out of Hospital","No Disease");
            return false;
        }

        healingDoctorChosen.takePatient(patient);
            System.out.printf("%s: %-40s go from %-60s with %-50s to %-60s with %-50s newSessionTime %-50s"+newLine, LocalDateTime.now().format(formatter),patient,doctor,"No Disease",healingDoctorChosen,patient.getDisease(),healingDoctorChosen.getLastPatientTimer().format(formatter));
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
        PriorityQueue<DiagnoseDoctor> queue=new PriorityQueue<>();
        for (DiagnoseDoctor doctor :
                list) {
            queue.add(doctor);
        }
        DIAGNOSE_DOCTOR_AVAILABLE.writeObjects(queue);
        DIAGNOSE_DOCTOR_INUSE.writeObjects(new PriorityQueue<>());
        inuse=new PriorityQueue<>();
        available=DIAGNOSE_DOCTOR_AVAILABLE.readObjects();
    }
    public void addNewDiagnoseDoctor(DiagnoseDoctor doctor) {
        list.add(doctor);
        DIAGNOSE_DOCTOR_LIST.writeObjects(list);
        available.add(doctor);
        DIAGNOSE_DOCTOR_AVAILABLE.writeObjects(available);
    }
}
