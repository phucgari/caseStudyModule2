package creationJunk;

import controler.DiagnoseDoctorPool;
import model.doctor.DiagnoseDoctor;

import java.util.PriorityQueue;


public class DiagnoseDoctorCreated {
    public static void main(String[] args) {
        DiagnoseDoctor doctor1=new DiagnoseDoctor("Diag1",1);
        DiagnoseDoctor doctor2=new DiagnoseDoctor("Diag2",2);
        DiagnoseDoctor doctor3=new DiagnoseDoctor("Diag3",3);
        DiagnoseDoctor doctor4=new DiagnoseDoctor("Diag4",4);
        DiagnoseDoctor doctor5=new DiagnoseDoctor("Diag5",2);
        PriorityQueue<DiagnoseDoctor> doctors=new PriorityQueue<>();
        doctors.add(doctor1);
        doctors.add(doctor5);
        doctors.add(doctor2);
        doctors.add(doctor3);
        doctors.add(doctor4);
//        while(!doctors.isEmpty()){
//            System.out.println(doctors.peek());
//            System.out.println(doctors.remove().getCurrent().getSessionTime());
//        }
        DiagnoseDoctorPool.DIAGNOSE_DOCTOR_LIST.writeObjects(doctors);
    }
}
