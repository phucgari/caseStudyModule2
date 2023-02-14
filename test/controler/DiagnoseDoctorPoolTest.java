package controler;

import model.doctor.DiagnoseDoctor;
import model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DiagnoseDoctorPoolTest {
    DiagnoseDoctorPool tester=new DiagnoseDoctorPool();
    @BeforeEach
    void init(){
        PatientManager.getInstance().emptyPatientQueue();
        assertEquals(0,PatientManager.getInstance().getPatientQueue().size());
    }

    @Test
    void testGet1Patient(){
        RuntimeException runtimeException=assertThrows(RuntimeException.class,()->{tester.getPatient();});
        assertEquals("No Patient in Queue",runtimeException.getMessage());
        addPatients(1);
        testGetPatient();
        addPatients(4);
        testGetPatient();
        testGetPatient();
    }
    @Test
    void testGet5Patient(){
        addPatients(5);
        for (int i = 0; i < 4; i++) {
            testGetPatient();
        }
        RuntimeException runtimeException=assertThrows(RuntimeException.class,()->{tester.getPatient();});
        assertEquals("No available DiagnoseDoctor",runtimeException.getMessage());
    }
    private static void addPatients(int numberOfPatient) {
        Patient[] patients=new Patient[6];
        patients[0]=new Patient("demo",false);
        patients[1]=new Patient("adam",true);
        patients[2]=new Patient("eva",false);
        patients[3]=new Patient("adam1",true);
        patients[4]=new Patient("eva1",false);

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

        assertEquals(patient, doctor.getCurrent());

        LocalDateTime newSessionTime= patient.getSessionTime();
        LocalDateTime expectedSessionTime=LocalDateTime.now().plusSeconds((long) (15/ doctor.getTimeMultiplier()));
        assertEquals(expectedSessionTime.getSecond(),newSessionTime.getSecond());
    }
}