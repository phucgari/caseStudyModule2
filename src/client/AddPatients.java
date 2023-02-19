package client;

import controler.DiagnoseDoctorPool;
import controler.HospitalManager;
import controler.PatientManager;
import controler.Runner;
import model.patient.Patient;

import java.util.Scanner;

public class AddPatients {
    static HospitalManager hospitalManager = HospitalManager.getInstance();
    static PatientManager patientManager = PatientManager.getInstance();
    public static void main(String[] args) {
        Runner runner=new Runner();
//        hospitalManager.flushAll();
        DiagnoseDoctorPool.getInstance().flushAvailableInuse();
        runner.start();
        boolean on=true;
        patientManager.generateDemoPatient(7);
        while (on){
            System.out.println("Adding Patient");
            System.out.println("Do you want to quit?");
            System.out.println("q for quit,other for add");
            Scanner scanner=new Scanner(System.in);
            String q=scanner.nextLine();
            if(q.equals("q"))break;
            patientManager.addPatientQueue(inputPatient());
        }
        runner.switchOnOff();
    }

    private static Patient inputPatient() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input Patient name");
        String name= scanner.nextLine();
        int input=-1;
        while (input>1||input<0){
            try {
                System.out.println("input gender:");
                System.out.println("0 for female");
                System.out.println("1 for male");
                input= Integer.parseInt(scanner.nextLine());
            }catch (Exception e){}
        }
        boolean gender=input==1;
        return new Patient(name,gender);
    }
}
