package controler;

import model.doctor.DiagnoseDoctor;
import model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class DiagnoseDoctorPoolTest {
    DiagnoseDoctorPool tester=new DiagnoseDoctorPool();
    @BeforeEach
    void init(){
        PatientManager.getInstance().emptyPatientQueue();
        assertEquals(0,PatientManager.getInstance().getPatientQueue().size());
        tester.flushAvailableInuse();
    }

    @Test
    void testGetFewPatient(){
        RuntimeException runtimeException=assertThrows(RuntimeException.class,()->{tester.getPatient();});
        assertEquals("No Patient in Queue",runtimeException.getMessage());
        addPatients(1);
        testGetPatient();
        addPatients(4);
        testGetPatient();
        testGetPatient();
    }
    @Test
    void testGet7Patient(){
        addPatients(6);
        for (int i = 0; i < 5; i++) {
            testGetPatient();
        }RuntimeException runtimeException=assertThrows(RuntimeException.class,()->{tester.getPatient();});
        assertEquals("No available DiagnoseDoctor",runtimeException.getMessage());
    }
    @Test
    void testPriorityInUse(){
        addPatients(6);
        for (int i = 0; i < 5; i++) {
            tester.getPatient();
        }
        PriorityQueue<DiagnoseDoctor> queue = tester.getInuse();
        assertEquals("DiagnoseDoctor name='Diag4' Experience: Leader\n", queue.remove().toString());
        assertEquals("DiagnoseDoctor name='Diag3' Experience: Senior\n", queue.remove().toString());
        assertEquals("DiagnoseDoctor name='Diag2' Experience: Junior\n", queue.remove().toString());
        assertEquals("DiagnoseDoctor name='Diag5' Experience: Junior\n", queue.remove().toString());
        assertEquals("DiagnoseDoctor name='Diag1' Experience: Fresher\n", queue.remove().toString());
    }
    @Test
    void testReleasePatient(){
        RuntimeException noInuseDoctor=assertThrows(RuntimeException.class,()->{tester.releasePatient();});
        assertEquals("No Inuse Doctor",noInuseDoctor.getMessage());
//        transferDocFromInUseToAvailable
//        change Patient Disease
//        change Patient sessionTime
//        change diagnoseDoc current to null
//        chose HealingDoc to push
//        then push Patient to HealingDocQueue
//        Serialize
        int patientQueueSize= PatientManager.getInstance().getPatientQueue().size();
        int availSize=tester.getAvailable().size();
        int inuseSize=tester.getInuse().size();

        DiagnoseDoctor doctor= tester.getInuse().peek();
        Patient patient=doctor.getCurrent();
        LocalDateTime time=patient.getSessionTime();
        tester.releasePatient();


    }
    private static void addPatients(int numberOfPatient) {
        Patient[] patients=new Patient[7];
        patients[0]=new Patient("demo",false);
        patients[1]=new Patient("adam",true);
        patients[2]=new Patient("eva",false);
        patients[3]=new Patient("adam1",true);
        patients[4]=new Patient("eva1",false);
        patients[5]=new Patient();
        patients[6]=new Patient();
        for (int i = 0; i < numberOfPatient; i++) {
            PatientManager.getInstance().addPatientQueue(patients[i]);
        }
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
        assertEquals(expectedSessionTime.getSecond(),newSessionTime.getSecond());
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