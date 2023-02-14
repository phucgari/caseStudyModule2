package controler;

import inputOutPut.QueueSerializer;
import model.doctor.DiagnoseDoctor;
import model.patient.Patient;

import java.util.Queue;

// design pattern Object pool
public class DiagnoseDoctorPool {
    private static String linkToDiagnoseDoctorListAndQueue="src/data/diagnoseDoctorLists";
    public static final QueueSerializer<DiagnoseDoctor> diagnoseDoctorAvailable=new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorAvailable.dat");
    public static final QueueSerializer<DiagnoseDoctor> diagnoseDoctorInuse=new QueueSerializer<>(linkToDiagnoseDoctorListAndQueue+"/diagnoseDoctorInuse.dat");
    private final Queue<DiagnoseDoctor> available= diagnoseDoctorAvailable.readObjects();
    private final Queue<DiagnoseDoctor> inuse= diagnoseDoctorInuse.readObjects();

    public Queue<DiagnoseDoctor> getAvailable() {
        return available;
    }

    public Queue<DiagnoseDoctor> getInuse() {
        return inuse;
    }

    public void getPatient() {
        if(PatientManager.getInstance().getPatientQueue().isEmpty())throw new RuntimeException("No Patient in Queue");
        if(available.isEmpty())throw new RuntimeException("No available DiagnoseDoctor");
        Patient patient=PatientManager.getInstance().removePatientQueue();
        DiagnoseDoctor doctor= transferDocFromAvailableToInUse();
        patient.setSessionTime(patient.getSessionTime().plusSeconds((long) (15/doctor.getTimeMultiplier())));
        doctor.setCurrent(patient);
    }

    private DiagnoseDoctor transferDocFromAvailableToInUse() {
        DiagnoseDoctor result = available.remove();
        inuse.add(result);
        return result;
    }
}
