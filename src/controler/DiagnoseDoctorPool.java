package controler;

import inputOutPut.QueueSerializer;
import inputOutPut.Serializer;
import model.doctor.DiagnoseDoctor;
import model.patient.Patient;

import java.util.LinkedList;
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

    }
}
