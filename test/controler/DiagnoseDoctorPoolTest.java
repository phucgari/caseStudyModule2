package controler;

import model.doctor.DiagnoseDoctor;
import model.patient.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DiagnoseDoctorPoolTest {
    DiagnoseDoctorPool tester=new DiagnoseDoctorPool();

    @Test
    void testGet1Patient(){
        Patient p1=new Patient();
        PatientManager.getInstance().addPatientQueue(p1);
        DiagnoseDoctor doctor= tester.getAvailable().peek();
        testGetPatient(p1, doctor,PatientManager.getInstance().getPatientQueue().size(),tester.getAvailable().size(),tester.getInuse().size());
    }
    @Test
    void testGet2Patient(){
        Patient p1=new Patient("adam",true);
        Patient p2=new Patient("eva",false);
        PatientManager.getInstance().addPatientQueue(p1);
        DiagnoseDoctor doctor= tester.getAvailable().peek();

        testGetPatient(p1, doctor, PatientManager.getInstance().getPatientQueue().size(),tester.getAvailable().size(),tester.getInuse().size());
    }
    private void testGetPatient(Patient patient, DiagnoseDoctor doctor,int patientQueueSize,int availSize,int inuseSize) {
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