package controler;

import inputOutPut.QueueSerializer;
import model.patient.Patient;

import java.util.PriorityQueue;


public class PatientManager {
    private static PatientManager instance;
    public static QueueSerializer<Patient> patientQueueSerializer=new QueueSerializer<>("src/data/patient/patientQueue.dat");
    private final PriorityQueue<Patient> patientQueue= PatientManager.patientQueueSerializer.readObjects();
    public PriorityQueue<Patient> getPatientQueue() {
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
    public void generateDemoPatient(int numberOfPatient) {
        Patient[] patients=new Patient[7];
        patients[0]=new Patient("adam",false);
        patients[1]=new Patient("demo",true);
        patients[2]=new Patient("eva",false);
        patients[3]=new Patient("adam1",true);
        patients[4]=new Patient("eva1",false);
        patients[5]=new Patient();
        patients[6]=new Patient();
        for (int i = 0; i < numberOfPatient; i++) {
            addPatientQueue(patients[i]);
        }
    }

    public void generateDemoPatientWith100SecEarly(int numberOfPatient) {
        Patient[] patients=new Patient[7];
        patients[0]=new Patient("adam",false);
        patients[1]=new Patient("demo",true);
        patients[2]=new Patient("eva",false);
        patients[3]=new Patient("adam1",true);
        patients[4]=new Patient("eva1",false);
        patients[5]=new Patient();
        patients[6]=new Patient();
        for (int i = 0; i < numberOfPatient; i++) {
            patients[i].setSessionTime(patients[i].getSessionTime().minusSeconds(100));
            addPatientQueue(patients[i]);
        }
    }
}
