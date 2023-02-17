package controler;

import model.doctor.DiagnoseDoctor;
import model.patient.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class RunnerTest {
    Runner run=new Runner();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private String newLine = System.getProperty("line.separator");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    void end(){
        HospitalManager.getInstance().flushAll();
        System.setOut(originalOut);
    }
    @Test
    void testCheckHealToOut(){
        run.checkHealToOut();
    }
    @Test
    void testCheckQueueToPool(){
        run.checkQueueToPool();
        PatientManager.getInstance().generateDemoPatient(6);
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
        run.checkQueueToPool();
        assertEquals(result,outContent.toString());
    }
    @Test
    void testCheckPoolToHeal(){

    }
}
