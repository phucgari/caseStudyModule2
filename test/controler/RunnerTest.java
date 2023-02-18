package controler;

import model.doctor.DiagnoseDoctor;
import model.doctor.HealingDoctor;
import model.patient.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerTest {
    Runner run=new Runner();
    private String newLine = System.getProperty("line.separator");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @AfterEach
    void end(){
        HospitalManager.getInstance().flushAll();
    }
    @Test
    void testCheckHealToOut(){
        PatientManager.getInstance().generateDemoPatient(6);
        run.checkQueueToPool();
        run.checkPoolToHeal();

        String result = getStringHealToOut();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        run.checkHealToOut();
        assertEquals(result,outContent.toString());
        System.setOut(originalOut);
    }


    @Test
    void testCheckQueueToPool(){
        run.checkQueueToPool();
        PatientManager.getInstance().generateDemoPatient(6);
        String result = getStringQueueToPool();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        run.checkQueueToPool();
        assertEquals(result,outContent.toString());
        System.setOut(originalOut);
    }
    @Test
    void testCheckPoolToHeal(){
        PatientManager.getInstance().generateDemoPatient(6);
        run.checkQueueToPool();

        Queue<Patient> patients = getPatientQueueToCheckString();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        run.checkPoolToHeal();
        String result = getStringPoolToHeal(patients);

        assertEquals(result,outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void testAll(){
        PatientManager.getInstance().generateDemoPatient(6);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        String result="";
        result+=getStringQueueToPool();
        run.checkQueueToPool();

        Queue<Patient>patients=getPatientQueueToCheckString();
        run.checkPoolToHeal();

        result+=getStringPoolToHeal(patients);
        result+=getStringHealToOut();
        run.checkHealToOut();

        assertEquals(result,outContent.toString());
        System.setOut(originalOut);
    }
    @Test
    void testALLINLarge(){
        for (int i = 0; i < 100; i++) {
            HospitalManager.getInstance().flushAll();
            testAll();
        }
    }
    private String findPatientInHealingDocReturnPrintedString(Patient patient) {
        List<HealingDoctor> healingDoctorList=HealingDoctorManager.getInstance().getHealingDoctorList();
        for (HealingDoctor doctor: healingDoctorList) {
            for (Patient patientInQueue: doctor.getPatientQueue()) {
                if(patientInQueue.getName().equals(patient.getName())) {
                    return LocalDateTime.now().format(formatter)+": "+ patient + " diagnose as " + patient.getDisease().getName() + " and send to " + doctor.getName() + " estimate Session Time " + patient.getSessionTime().format(formatter)+newLine;
                }
            }
        }
        throw new RuntimeException();
    }
    private String getStringHealToOut() {
        String result="";
        List<HealingDoctor> healingDoctorList=new ArrayList<>(HealingDoctorManager.getInstance().getHealingDoctorList());
        for (HealingDoctor doctor : healingDoctorList) {
            PriorityQueue<Patient> queue = new PriorityQueue<>(doctor.getPatientQueue());
            while (!queue.isEmpty()){
                Patient patient = queue.remove();
                String str=LocalDateTime.now().format(formatter)+": "+patient + " has cured " + patient.getDisease() + " at " + patient.getSessionTime().format(formatter) + " by " + doctor+newLine;
                if (!result.contains(str))result += str;
            }
        }
        return result;
    }

    private String getStringQueueToPool() {
        String result= new String();
        PriorityQueue<Patient> patients=new PriorityQueue<>(PatientManager.getInstance().getPatientQueue());
        PriorityQueue<DiagnoseDoctor> doctors=new PriorityQueue<>(DiagnoseDoctorPool.getInstance().getAvailable());
        Patient patient;
        DiagnoseDoctor doctor;
        for (int i = 0; i < 5; i++) {
            patient=patients.remove();
            doctor=doctors.remove();
            LocalDateTime patientTime = LocalDateTime.now().plusSeconds((long) (15 / doctor.getTimeMultiplier()));
            result+=LocalDateTime.now().format(formatter)+": "+patient+" started diagnose, finish at "+patientTime.format(formatter)+" by "+doctor+newLine;
        }
        return result;
    }

    private Queue<Patient> getPatientQueueToCheckString() {
        Queue<Patient> patients=new LinkedList<>();

        PriorityQueue<DiagnoseDoctor>Inuse=new PriorityQueue<>(DiagnoseDoctorPool.getInstance().getInuse());

        for (int i = 0; i < 5; i++) {
            Patient current = Inuse.remove().getCurrent();
            current.setSessionTime(current.getSessionTime().minusSeconds(100));
            patients.add(current);
        }
        return patients;

    }

    private String getStringPoolToHeal(Queue<Patient> patients){
        String result=new String();
        while (!patients.isEmpty()) {
            Patient patient= patients.remove();
            if(patient.getDisease().getName().equals("No Disease")) {
                result+=LocalDateTime.now().format(formatter)+": "+ patient+" have no Disease"+newLine;
                continue;
            }
            result+= findPatientInHealingDocReturnPrintedString(patient);
        }
        return result;
    }
}
