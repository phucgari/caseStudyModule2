package creationJunk;

import controler.PatientManager;
import model.patient.Patient;

import java.util.PriorityQueue;

public class PatientQueueCreated {
    public static void main(String[] args) {
        PriorityQueue<Patient>patients=new PriorityQueue<>();
        PatientManager.patientQueueSerializer.writeObjects(patients);
    }
}
