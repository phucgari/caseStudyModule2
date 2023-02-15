package controler;

import inputOutPut.QueueSerializer;
import model.doctor.DiagnoseDoctor;
import model.patient.Patient;

import java.util.PriorityQueue;

// design pattern Object pool
public class DiagnoseDoctorPool {
    private static final String linkToDiagnoseDoctorListAndQueue="src/data/diagnoseDoctorLists";
    public static final QueueSerializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_LIST =new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorList.dat");
    public static final QueueSerializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_AVAILABLE =new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorAvailable.dat");
    public static final QueueSerializer<DiagnoseDoctor> DIAGNOSE_DOCTOR_INUSE =new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorInuse.dat");
    private PriorityQueue<DiagnoseDoctor> available= DIAGNOSE_DOCTOR_AVAILABLE.readObjects();
    private PriorityQueue<DiagnoseDoctor> inuse= DIAGNOSE_DOCTOR_INUSE.readObjects();

    public PriorityQueue<DiagnoseDoctor> getAvailable() {
        return available;
    }

    public PriorityQueue<DiagnoseDoctor> getInuse() {
        return inuse;
    }

    public void getPatient() {
        if(PatientManager.getInstance().getPatientQueue().isEmpty())throw new RuntimeException("No Patient in Queue");
        if(available.isEmpty())throw new RuntimeException("No available DiagnoseDoctor");
        Patient patient=PatientManager.getInstance().removePatientQueue();
        DiagnoseDoctor doctor= available.remove();
        doctor.setCurrent(patient);
        transferDocToInUse(doctor);

        patient.setSessionTime(patient.getSessionTime().plusSeconds((long) (15/doctor.getTimeMultiplier())));
    }
    public void releasePatient(){
//        throw exception
//        transferDocFromInUseToAvailable
//        change Patient sessionTime
//        change diagnoseDoc current to null
//

    }
    public void flushAvailableInuse(){
        DIAGNOSE_DOCTOR_AVAILABLE.writeObjects(DIAGNOSE_DOCTOR_LIST.readObjects());
        inuse.clear();
        DIAGNOSE_DOCTOR_INUSE.writeObjects(inuse);
        available= DIAGNOSE_DOCTOR_AVAILABLE.readObjects();
        inuse= DIAGNOSE_DOCTOR_INUSE.readObjects();
    }

    private void transferDocToInUse(DiagnoseDoctor doctor) {
        inuse.add(doctor);
        DIAGNOSE_DOCTOR_AVAILABLE.writeObjects(available);
        DIAGNOSE_DOCTOR_INUSE.writeObjects(inuse);
    }
}
