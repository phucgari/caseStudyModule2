package controler;

import model.doctor.*;
import model.patient.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class DiagnoseDoctorPoolTest {
    DiagnoseDoctorPool tester=DiagnoseDoctorPool.getInstance();

    private String newLine = System.getProperty("line.separator");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeAll
    public static void setUpStreams() {
        HospitalManager.getInstance().flushAll();
    }
    @AfterEach
    void end(){
        HospitalManager.getInstance().flushAll();
    }

    @Test
    void testGetFewPatient(){
        RuntimeException runtimeException=assertThrows(RuntimeException.class,()->{tester.getPatient();});
        assertEquals("No Patient in Queue",runtimeException.getMessage());
        PatientManager.getInstance().generateDemoPatient(1);
        testGetPatient();
        PatientManager.getInstance().generateDemoPatient(4);
        testGetPatient();
        testGetPatient();
    }
    @Test
    void testGet7Patient(){
        PatientManager.getInstance().generateDemoPatient(6);
        for (int i = 0; i < 5; i++) {
            testGetPatient();
        }RuntimeException runtimeException=assertThrows(RuntimeException.class,()->{tester.getPatient();});
        assertEquals("No available DiagnoseDoctor",runtimeException.getMessage());
    }
    @Test
    void testPriorityInUse(){
        PatientManager.getInstance().generateDemoPatient(6);
        for (int i = 0; i < 5; i++) {
            tester.getPatient();
        }
        PriorityQueue<DiagnoseDoctor> queue = tester.getInuse();
        assertEquals("DiagnoseDoctor name='Diag4' Experience: Leader", queue.remove().toString());
        assertEquals("DiagnoseDoctor name='Diag3' Experience: Senior", queue.remove().toString());
        assertEquals("DiagnoseDoctor name='Diag2' Experience: Junior", queue.remove().toString());
        assertEquals("DiagnoseDoctor name='Diag5' Experience: Junior", queue.remove().toString());
        assertEquals("DiagnoseDoctor name='Diag1' Experience: Fresher", queue.remove().toString());
    }
    @Test
    void testRelease1Patient(){
        RuntimeException noInuseDoctor=assertThrows(RuntimeException.class,()->{tester.releasePatient();});
        assertEquals("No Inuse Doctor",noInuseDoctor.getMessage());
        PatientManager.getInstance().generateDemoPatient(3);
        tester.getPatient();
        testReleasePatient();
        tester.getPatient();
        tester.getPatient();
        testReleasePatient();
        testReleasePatient();
    }
    @Test
    void testRemoveDoc(){
        tester.removeDiagnoseDoctor(0);
        assertEquals("[DiagnoseDoctor name='Diag5' Experience: Junior, DiagnoseDoctor name='Diag2' Experience: Junior, DiagnoseDoctor name='Diag3' Experience: Senior, DiagnoseDoctor name='Diag4' Experience: Leader]",tester.getList().toString());
        tester.removeDiagnoseDoctor(3);
        assertEquals("[DiagnoseDoctor name='Diag5' Experience: Junior, DiagnoseDoctor name='Diag2' Experience: Junior, DiagnoseDoctor name='Diag3' Experience: Senior]",tester.getList().toString());
        tester.removeDiagnoseDoctor(1);
        assertEquals("[DiagnoseDoctor name='Diag5' Experience: Junior, DiagnoseDoctor name='Diag3' Experience: Senior]",tester.getList().toString());
    }

    private void testReleasePatient() {
        int availSize=tester.getAvailable().size();
        int inuseSize=tester.getInuse().size();

        DiagnoseDoctor doctor= tester.getInuse().peek();
        Patient patient=doctor.getCurrent();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        boolean result=tester.releasePatient();
        assertEquals(availSize+1,tester.getAvailable().size());
        assertEquals(inuseSize-1,tester.getInuse().size());
        assertEquals("prevent null",doctor.getCurrent().getName());
        if (!result){
            String resultStr=outContent.toString();

            String expected=String.format("%s: %-40s go from %-60s with %-50s to %-60s with %-50s"+newLine, LocalDateTime.now().format(formatter),patient,doctor,"No Disease","out of Hospital","No Disease");
            assertEquals(expected,resultStr);
            System.setOut(originalOut);
            return;
        }
        HealingDoctor healingDoctor=findDocWithPatient(patient);
//        then push Patient to HealingDocQueue
        checkHealingDoctorContainPatient(healingDoctor,patient);
//        change Patient sessionTime
        assertEquals(patient.getSessionTime(),healingDoctor.getLastPatientTimer());
//        Serialize
        assertTrue(HealingDoctorManager.getInstance().getHealingDoctorList().contains(healingDoctor));
        String str =String.format("%s: %-40s go from %-60s with %-50s to %-60s with %-50s newSessionTime %-50s"+newLine, LocalDateTime.now().format(formatter),patient,doctor,"No Disease",healingDoctor,patient.getDisease(),patient.getSessionTime().format(formatter));
        String newContent=outContent.toString();
        assertEquals(str,newContent);
        System.setOut(originalOut);
    }

    private void checkHealingDoctorContainPatient(HealingDoctor healingDoctor, Patient patient) {
        Queue<Patient>queue=healingDoctor.getPatientQueue();
        for (Patient p :
                queue) {
            if(p.getName().equals(patient.getName())){
                assertEquals(patient,p);
                return ;
            }
        }
        throw new RuntimeException("Healing doctor queue does not contain patient");
    }

    private HealingDoctor findDocWithPatient(Patient patient) {
        for (HealingDoctor doctor :
                HealingDoctorManager.getInstance().getHealingDoctorList()) {
            for (Patient p :
                    doctor.getPatientQueue()) {
                if (p.getName().equals(patient.getName()))return doctor;
            }
        }
        return null;
    }

    private void testGetPatient() {
        int patientQueueSize= PatientManager.getInstance().getPatientQueue().size();
        int availSize=tester.getAvailable().size();
        int inuseSize=tester.getInuse().size();

        DiagnoseDoctor doctor= tester.getAvailable().peek();
        Patient patient=PatientManager.getInstance().getPatientQueue().peek();

        assertEquals(patientQueueSize,PatientManager.getInstance().getPatientQueue().size());
        tester.getPatient();

        assertEquals(availSize-1,tester.getAvailable().size());
        assertEquals(inuseSize+1,tester.getInuse().size());
        assertEquals(patientQueueSize-1,PatientManager.getInstance().getPatientQueue().size());

        LocalDateTime newSessionTime= patient.getSessionTime();
        LocalDateTime expectedSessionTime=LocalDateTime.now().plusSeconds((long) (15/ doctor.getTimeMultiplier()));
        assertEquals(expectedSessionTime.format(formatter),newSessionTime.format(formatter));
        PriorityQueue<DiagnoseDoctor> queue=tester.getInuse();
        for (DiagnoseDoctor doctor1:queue) {
            boolean boo=doctor.getName().equals(doctor1.getName());
            if(boo){
                doctor=doctor1;
                break;
            }
        }
        assertEquals(patient.toString(), doctor.getCurrent().toString());
    }
}