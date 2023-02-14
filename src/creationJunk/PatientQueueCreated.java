package creationJunk;

import controler.PatientManager;
import model.patient.Patient;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PatientQueueCreated {
    public static void main(String[] args) {
        Queue<Patient>patients=new LinkedList<>();
        PatientManager.patientQueueSerializer.writeObjects(patients);
    }
}
