package client;

import controler.HospitalManager;
import controler.PatientManager;
import controler.Runner;

public class AddPatients {
    static HospitalManager hospitalManager = HospitalManager.getInstance();
    static PatientManager patientManager = PatientManager.getInstance();
    public static void main(String[] args) {
        Runner runner=new Runner();
        hospitalManager.flushAll();
        patientManager.generateDemoPatient(7);

    }
}
