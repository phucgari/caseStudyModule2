package controler;

import inputOutPut.QueueSerializer;
import model.patient.Patient;

import java.util.Queue;

public class PatientManager {
    private static PatientManager instance;
    public static QueueSerializer<Patient> patientQueueSerializer=new QueueSerializer<>("src/data/patient/patientQueue.dat");
    private final Queue<Patient> patientQueue= PatientManager.patientQueueSerializer.readObjects();
    public Queue<Patient> getPatientQueue() {
        return patientQueue;
    }
    private PatientManager(){}
    public static PatientManager getInstance(){
        if (instance==null)instance=new PatientManager();
        return instance;
    }
    public void addPatientQueue(Patient patient){
        patientQueue.add(patient);
        patientQueueSerializer.writeObjects(patientQueue);
    }
    public Patient removePatientQueue(){
        Patient result = patientQueue.remove();
        patientQueueSerializer.writeObjects(patientQueue);
        return result;
    }
    public void emptyPatientQueue(){
        patientQueue.clear();
        patientQueueSerializer.writeObjects(patientQueue);
    }
}
