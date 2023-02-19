package client;

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
        hospitalManager.flushAll();
        runner.start();
        boolean on=true;
        patientManager.generateDemoPatient(5);
        while (on){
            patientManager.addPatientQueue(inputPatient());
        }
        runner.switchOnOff();
    }

    private static Patient inputPatient() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("input name");
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
