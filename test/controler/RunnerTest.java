package controler;

import model.doctor.DiagnoseDoctor;
import model.doctor.HealingDoctor;
import model.patient.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
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
    @Disabled
    void testALLINLarge(){
        for (int i = 0; i < 100; i++) {
            HospitalManager.getInstance().flushAll();
            testAll();
        }
    }
    private String findPatientInHealingDocReturnPrintedString(Patient patient, String diagnoseDoctor) {
        List<HealingDoctor> healingDoctorList=HealingDoctorManager.getInstance().getHealingDoctorList();
        for (HealingDoctor healingDoctor: healingDoctorList) {
            for (Patient patientInQueue: healingDoctor.getPatientQueue()) {
                if(patientInQueue.getName().equals(patient.getName())) {
                    return String.format("%s: %-40s go from %-60s with %-50s to %-60s with %-50s newSessionTime %-50s"+newLine, LocalDateTime.now().format(formatter),patient,diagnoseDoctor,"No Disease",healingDoctor,patient.getDisease(),patient.getSessionTime().format(formatter));
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
                String str=String.format("%s: %-40s go from %-60s with %-50s to %-60s with %-50s at SessionTime %-50s"+newLine, LocalDateTime.now().format(formatter),patient,doctor,patient.getDisease(),"out of hospital","No Disease",patient.getSessionTime().format(formatter));
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
            result+=String.format("%s: %-40s go from %-60s with %-50s to %-60s with %-50s newSessionTime %-50s"+newLine, LocalDateTime.now().format(formatter),patient,"Queue","No Disease",doctor,"No Disease",patient.getSessionTime().plusSeconds((long) (15/doctor.getTimeMultiplier())).format(formatter));
        }
        return result;
    }

    private Queue<Patient> getPatientQueueToCheckString() {
        PriorityQueue<DiagnoseDoctor>diagnoseDoctors=new PriorityQueue<>(DiagnoseDoctorPool.getInstance().getInuse());
        Queue<Patient> patients=new LinkedList<>();
        PriorityQueue<DiagnoseDoctor>inuse=new PriorityQueue<>(diagnoseDoctors);
        for (int i = 0; i < 5; i++) {
            Patient current = inuse.remove().getCurrent();
            current.setSessionTime(current.getSessionTime().minusSeconds(100));
            patients.add(current);
        }
        return patients;

    }

    private String getStringPoolToHeal(Queue<Patient> patients){
        String result=new String();
        String[]doctors=new String[]{
                "DiagnoseDoctor name='Diag4' Experience: Leader ",
                "DiagnoseDoctor name='Diag3' Experience: Senior",
                "DiagnoseDoctor name='Diag2' Experience: Junior",
                "DiagnoseDoctor name='Diag5' Experience: Junior",
                "DiagnoseDoctor name='Diag1' Experience: Fresher"};
        for (int i = 0; i < 5; i++) {
            Patient patient= patients.remove();
            String doctor=doctors[i];
            if(patient.getDisease().getName().equals("No Disease")) {
                result+=String.format("%s: %-40s go from %-60s with %-50s to %-60s with %-50s"+newLine, LocalDateTime.now().format(formatter),patient,doctor,"No Disease","out of Hospital","No Disease");
                continue;
            }
            result+= findPatientInHealingDocReturnPrintedString(patient,doctor);
        }
        return result;
    }
}
