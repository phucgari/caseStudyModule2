package creationJunk;

import controler.DiagnoseDoctorPool;
import model.doctor.DiagnoseDoctor;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DiagnoseDoctorCreated {
    public static void main(String[] args) {
        DiagnoseDoctor doctor1=new DiagnoseDoctor("Diag1",1);
        DiagnoseDoctor doctor2=new DiagnoseDoctor("Diag2",2);
        DiagnoseDoctor doctor3=new DiagnoseDoctor("Diag3",3);
        DiagnoseDoctor doctor4=new DiagnoseDoctor("Diag4",4);
        Queue<DiagnoseDoctor> doctors=new LinkedList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);
        doctors.add(doctor3);
        doctors.add(doctor4);
        DiagnoseDoctorPool.diagnoseDoctorAvailable.writeObjects(doctors);
    }
}
