package controler;

import inputOutPut.QueueSerializer;
import inputOutPut.Serializer;
import model.patient.Patient;

import java.util.List;
import java.util.Queue;

public class PatientManager {
    private static PatientManager instance;
    public static QueueSerializer<Patient> patientQueueSerializer=new QueueSerializer<>("patientQueue.dat");
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
}
