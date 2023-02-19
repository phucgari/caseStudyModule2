package client;

import controler.DiagnoseDoctorPool;
import controler.HospitalManager;
import model.doctor.DiagnoseDoctor;

import java.util.List;
import java.util.Scanner;

public class CRUDDiagnoseDoctor {
    static HospitalManager hospitalManager = HospitalManager.getInstance();
    static DiagnoseDoctorPool diagnoseDoctorPool=DiagnoseDoctorPool.getInstance();
    public static void main(String[] args) {
//        hospitalManager.flushAll();
        boolean on=true;
//        patientManager.generateDemoPatient(5);
        Scanner scanner=new Scanner(System.in);
        String input="";
        while (on){
            System.out.println("Input");
            System.out.println("1 for add new DiagnoseDoctor");
            System.out.println("2 for view and delete Diagnose Doctor");
            System.out.println("3 for quit");
            input= scanner.nextLine();
            switch (input){
                case "1":
                    addDiagnoseDoctor();
                    break;
                case "2":
                    viewAndDeleteDoctor();
                    break;
                case "3":
                    on=false;
                    break;
            }
        }
    }

    private static void viewAndDeleteDoctor() {
        Scanner scanner=new Scanner(System.in);
        int input=0;
        String num="[0-9]+";
        while(true){
            List<DiagnoseDoctor> list=diagnoseDoctorPool.getList();
            if(list.isEmpty()){
                System.out.println("no available DiagnoseDoctor");
                return;
            }
            System.out.println("View and delete DiagnoseDoctor chosen");
            for (DiagnoseDoctor doctor :
                    list) {
                System.out.println(doctor);
            }
            System.out.println("Input your action");

            System.out.println("numbers for index deletion");
            System.out.println("-1 for quit");
            String inputStr= scanner.nextLine();
            if(inputStr.equals("-1"))break;
            if(inputStr.matches(num))input= Integer.parseInt(inputStr);

            if(input<list.size()&&input>-1){
                diagnoseDoctorPool.removeDiagnoseDoctor(input);
            }
        }
    }

    private static void addDiagnoseDoctor() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("add new Diagnose Doctor");
        System.out.println("input DiagnoseDoctor name:");
        String name=scanner.nextLine();
        int experience=-1;
        while (experience>4||experience<1) {
            try {
                System.out.println("input DiagnoseDoctor Experience");
                experience = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid experience, try again");
                experience =-1;
            }
        }
        DiagnoseDoctor doctor=new DiagnoseDoctor(name,experience);
        diagnoseDoctorPool.addNewDiagnoseDoctor(doctor);
    }
}
