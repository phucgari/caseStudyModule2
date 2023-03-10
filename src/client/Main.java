package client;

import controler.*;
import inputOutPut.FileReaderWriter;
import model.doctor.DiagnoseDoctor;
import model.doctor.Disease;
import model.doctor.HealingDoctor;
import model.patient.Patient;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final PatientManager patientManager = PatientManager.getInstance();
    private static final DiagnoseDoctorPool diagnoseDoctorPool=DiagnoseDoctorPool.getInstance();
    private static final HealingDoctorManager healingDoctorManager = HealingDoctorManager.getInstance();
    private static final DiseaseManager diseaseManager = DiseaseManager.getInstance();
    private static final FileReaderWriter readerWriter= new FileReaderWriter("src/data/sout.txt");
    public static void main(String[] args) {
        readerWriter.delete();
        HospitalManager hospitalManager =HospitalManager.getInstance();
        hospitalManager.start();
        Scanner scanner=new Scanner(System.in);
        generatePatient(scanner);
        mainMenu(scanner);
        hospitalManager.switchOnOff();
    }

    private static void generatePatient(Scanner scanner) {
        System.out.println("Do you want input demo Patients?");
        while(true) {
            try {
                System.out.println("input the number of demo Patients to add (max:7)");
                System.out.println("0 to skip");
                int patientNum = Math.min(Integer.parseInt(scanner.nextLine()), 7);
                if (patientNum == 0) break;
                System.out.println("added " + patientNum + " patient(s)");
                patientManager.generateDemoPatient(patientNum);
                break;
            } catch (Exception ignored) {
            }
        }
    }

    private static void mainMenu(Scanner scanner) {
        boolean on=true;
        while (on){
            System.out.println(" _____________________________________________ ");
            System.out.println("|               HostpitalManager              |");
            System.out.println("|     1 for add Patient                       |");
            System.out.println("|     2 for access Diagnose Doctor            |");
            System.out.println("|     3 for access Healing Doctor             |");
            System.out.println("|     4 for viewing/adding new Disease        |");
            System.out.println("|     0 for quit                              |");
            System.out.println(" _____________________________________________ ");

            String choice= scanner.nextLine();
            switch (choice){
                case "1":
                    addPatient(scanner);
                    break;
                case "2":
                    accessDiagnoseDoctor(scanner);
                    break;
                case "3":
                    accessHealingDoctor(scanner);
                    break;
                case "4":
                    addNewDisease(scanner);
                    break;
                case "0":
                    on=false;
            }
        }
    }

    private static void addNewDisease(Scanner scanner) {
        while (true) {
            System.out.println("List of Disease");
            List<Disease>list=diseaseManager.getList();
            for (Disease disease :
                    list) {
                System.out.println(disease);
            }
            System.out.println(" _____________________________________________ ");
            System.out.println("|     Select Disease type?                    |");
            System.out.println("|     1 for Dentist                           |");
            System.out.println("|     2 for Gynecologist                      |");
            System.out.println("|     3 for Urologist                         |");
            System.out.println("|     4 for Otolaryngologist                  |");
            System.out.println("|     5 for Surgeon                           |");
            System.out.println("|     0 for exit                              |");
            System.out.println(" _____________________________________________ ");
            String action = scanner.nextLine();
            if(action.equals("0"))break;
            else if (!action.matches("[1-5]")) continue;
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            String expInt;
            do {
                System.out.println("Enter Cure time");
                System.out.println("Enter 0 to 99");
                expInt = scanner.nextLine();
            } while (!expInt.matches("[0-9][0-9]"));
            int cureTime = Integer.parseInt(expInt);
            switch (action) {
                case "1":
                    diseaseManager.addDentistDisease(name, cureTime);
                    break;
                case "2":
                    diseaseManager.addGynecologistDisease(name, cureTime);
                    break;
                case "3":
                    diseaseManager.addUrologistDisease(name, cureTime);
                    break;
                case "4":
                    diseaseManager.addOtolaryngologistDisease(name, cureTime);
                    break;
                case "5":
                    diseaseManager.addSurgeonDisease(name, cureTime);
                    break;
            }
        }
    }

    private static void accessHealingDoctor(Scanner scanner) {
        boolean on=true;
        while (on){
            System.out.println(" _____________________________________________ ");
            System.out.println("|     Healing doctor Manager                  |");
            System.out.println("|     1 for add new Healing Doctor            |");
            System.out.println("|     2 for view and delete Healing Doctor    |");
            System.out.println("|     0 for quit                              |");
            System.out.println(" _____________________________________________ ");
            String input = scanner.nextLine();
            switch (input){
                case "1":
                    addHealingDoc(scanner);
                    break;
                case "2":
                    viewAndDeleteHealingDoctor(scanner);
                    break;
                case "0":
                    on=false;
                    break;
            }
        }
    }

    private static void viewAndDeleteHealingDoctor(Scanner scanner) {
        System.out.println(" _____________________________________________________________________ ");
        System.out.println("|     View and Delete Healing Doctor chosen                           |");
        int input=0;
        while (true) {
            List<HealingDoctor> list = healingDoctorManager.getHealingDoctorList();
            if (list.isEmpty()) {
        System.out.println("|     no available HealingDoctor                                      |");
        System.out.println(" _____________________________________________________________________ ");
                return;
            }
            int index = 1;
            for (HealingDoctor doctor :
                    list) {
          System.out.printf("|     %-3s %-60s|\n",index,doctor.toString());
                index++;
            }
        System.out.println(" _____________________________________________________________________ ");

            System.out.println("Input your action");
            System.out.println("numbers for index deletion");
            System.out.println("0 for quit");
            String inputStr= scanner.nextLine();
            if(inputStr.equals("0"))break;
            if(inputStr.matches("[0-9]+"))input= Integer.parseInt(inputStr);

            if(input<list.size()&&input>-1){
                healingDoctorManager.remove(input-1);
            }
        }
    }

    private static void addHealingDoc(Scanner scanner) {
        while (true) {
            System.out.println(" _____________________________________________ ");
            System.out.println("|     Select Healing doc type?                |");
            System.out.println("|     1 for Dentist                           |");
            System.out.println("|     2 for Gynecologist                      |");
            System.out.println("|     3 for Urologist                         |");
            System.out.println("|     4 for Otolaryngologist                  |");
            System.out.println("|     5 for Surgeon                           |");
            System.out.println("|     0 for exit                              |");
            System.out.println(" _____________________________________________ ");
            String action = scanner.nextLine();
            if(action.equals("0"))break;
            else if(!action.matches("[1-5]"))continue;
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            String expInt;
            do {
                System.out.println("Enter Experience ID");
                System.out.println("Enter 1 to 4");
                expInt = scanner.nextLine();
            } while (!expInt.matches("[1-4]"));
            int exp = Integer.parseInt(expInt);
            switch (action) {
                case "1":
                    healingDoctorManager.addDentist(name, exp);
                    break;
                case "2":
                    healingDoctorManager.addGynecologist(name, exp);
                    break;
                case "3":
                    healingDoctorManager.addUrologist(name, exp);
                    break;
                case "4":
                    healingDoctorManager.addOtolaryngologist(name, exp);
                    break;
                case "5":
                    healingDoctorManager.addSurgeon(name, exp);
                    break;
            }
        }
    }

    private static void addPatient(Scanner scanner) {
        while (true){
        System.out.println(" _____________________________________________ ");
        System.out.println("|     Adding Patient                          |");
        System.out.println("|     Do you want to quit?                    |");
        System.out.println("|     0 for quit,1 for add                    |");
        System.out.println(" _____________________________________________ ");
        String q=scanner.nextLine();
        if(q.equals("0")) return;
        else if(!q.equals("1"))continue;
        patientManager.addPatientQueue(inputPatient(scanner));
        }
    }

    private static Patient inputPatient(Scanner scanner) {
        System.out.println("Input Patient name");
        String name= scanner.nextLine();
        int input=-1;
        while (input>1||input<0){
            try {
                System.out.println("input gender:");
                System.out.println("0 for female");
                System.out.println("1 for male");
                input= Integer.parseInt(scanner.nextLine());
            }catch (Exception ignored){}
        }
        boolean gender=input==1;
        return new Patient(name,gender);
    }

    private static void accessDiagnoseDoctor(Scanner scanner) {
        boolean on=true;
        while (on){
            System.out.println("Input");
            System.out.println("1 for add new DiagnoseDoctor");
            System.out.println("2 for view and delete Diagnose Doctor");
            System.out.println("0 for quit");
            String input = scanner.nextLine();
            switch (input){
                case "1":
                    addDiagnoseDoctor(scanner);
                    break;
                case "2":
                    viewAndDeleteDiagnoseDoctor(scanner);
                    break;
                case "0":
                    on=false;
                    break;
            }
        }
    }

    private static void viewAndDeleteDiagnoseDoctor(Scanner scanner) {
        int input=0;
        String num="[0-9]+";
        while(true){
            List<DiagnoseDoctor> list=diagnoseDoctorPool.getList();
            System.out.println(" _____________________________________________________________________ ");
            System.out.println("|     View and Delete Diagnose Doctor chosen                          |");
            if(list.isEmpty()){
            System.out.println("|     No available DiagnoseDoctor                                     |");
            System.out.println(" _____________________________________________________________________ ");
                return;
            }
            int index=1;
            for (DiagnoseDoctor doctor :
                    list) {
            System.out.printf("|     %-3s %-60s|\n",index,doctor.toString());
                index++;
            }
            System.out.println(" _____________________________________________________________________ ");
            System.out.println("Input your action");
            System.out.println("numbers for index deletion");
            System.out.println("0 for quit");
            String inputStr= scanner.nextLine();
            if(inputStr.equals("0"))break;
            if(inputStr.matches(num))input= Integer.parseInt(inputStr);

            if(input<list.size()&&input>-1){
                diagnoseDoctorPool.removeDiagnoseDoctor(input-1);
            }
        }
    }

    private static void addDiagnoseDoctor(Scanner scanner) {
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
