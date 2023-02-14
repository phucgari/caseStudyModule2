package controler;

import inputOutPut.Serializer;
import model.doctor.DiagnoseDoctor;
import model.patient.Patient;

import java.util.LinkedList;
import java.util.Queue;

// design pattern Object pool
public class DiagnoseDoctorPool {
    public static final Serializer<DiagnoseDoctor> serializer=new Serializer<>("src/data/diagnoseDoctorList.dat");
    private final Queue<DiagnoseDoctor> available= (Queue<DiagnoseDoctor>) serializer.readObjects();
    private final Queue<DiagnoseDoctor> inuse= new LinkedList<>();
    private final Queue<Patient>patientQueue=new LinkedList<>();

    public Queue<Patient> getPatientQueue() {
        return patientQueue;
    }

    public Queue<DiagnoseDoctor> getAvailable() {
        return available;
    }

    public Queue<DiagnoseDoctor> getInuse() {
        return inuse;
    }
}
