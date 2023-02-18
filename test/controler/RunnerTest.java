package controler;

import model.doctor.DiagnoseDoctor;
import model.doctor.HealingDoctor;
import model.patient.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    @BeforeEach
    @AfterEach
    void end(){
        HospitalManager.getInstance().flushAll();
    }
    @Test
    void testCheckHealToOut(){
        PatientManager.getInstance().generateDemoPatientWith100SecEarly(6);
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
        PatientManager.getInstance().generateDemoPatientWith100SecEarly(6);
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
        PatientManager.getInstance().generateDemoPatientWith100SecEarly(6);
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
    private String findPatientInHealingDocReturnPrintedString(Patient patient) {
        List<HealingDoctor> healingDoctorList=HealingDoctorManager.getInstance().getHealingDoctorList();
        for (HealingDoctor doctor: healingDoctorList) {
            for (Patient patientInQueue: doctor.getPatientQueue()) {
                if(patientInQueue.getName().equals(patient.getName())) {
                    return patient + " diagnose as " + patient.getDisease().getName() + " and send to " + doctor.getName() + " estimate Session Time " + patient.getSessionTime().format(formatter)+newLine;
                }
            }
        }
        return "";
//        throw new RuntimeException();
    }
    private String getStringHealToOut() {
        String result="";
        List<HealingDoctor> healingDoctorList=new ArrayList<>(HealingDoctorManager.getInstance().getHealingDoctorList());
        int counter=0;
        while (counter<healingDoctorList.size()) {
            counter=0;
            for (HealingDoctor doctor : healingDoctorList) {
                PriorityQueue<Patient> queue = new PriorityQueue<>(doctor.getPatientQueue());
                if (queue.isEmpty()) counter++;
                else {
                    Patient patient = queue.remove();
                    String str=patient + " has cured " + patient.getDisease() + " at " + patient.getSessionTime() + " by " + doctor+newLine;
                    if (result.contains(str)){
                        counter++;
                        continue;
                    }
                    result += str;
                }
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
            result+=patient+" started diagnose, finish at "+patientTime.format(formatter)+" by "+doctor+newLine;
        }
        return result;
    }

    private static Queue<Patient> getPatientQueueToCheckString() {
        Queue<Patient> patients=new LinkedList<>();

        PriorityQueue<DiagnoseDoctor>Inuse=new PriorityQueue<>(DiagnoseDoctorPool.getInstance().getInuse());

        for (int i = 0; i < 5; i++) {
            Patient current = Inuse.remove().getCurrent();
            current.setSessionTime(current.getSessionTime().minusSeconds(30));
            patients.add(current);
        }
        return patients;

    }

    private String getStringPoolToHeal(Queue<Patient> patients){
        String result=new String();
        while (!patients.isEmpty()) {
            Patient patient= patients.remove();
            if(patient.getDisease().getName().equals("No Disease")) {
                result+= patient+" have no Disease"+newLine;
                continue;
            }
            result+= findPatientInHealingDocReturnPrintedString(patient);
        }
        return result;
    }
}
