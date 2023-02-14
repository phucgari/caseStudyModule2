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
        tester.getPatientQueue().add(p1);
        assertEquals(1,tester.getPatientQueue());
        DiagnoseDoctor doctor= tester.getAvailable().peek();

        tester.getPatient();

        assertEquals(3,tester.getAvailable().size());
        assertEquals(1,tester.getInuse().size());
        assertEquals(0,tester.getPatientQueue().size());

        assertEquals(p1,doctor.getCurrent());

        LocalDateTime newSessionTime=p1.getSessionTime();
        LocalDateTime expectedSessionTime=LocalDateTime.now().plusSeconds((long) (15/doctor.getTimeMultiplier()));
        assertEquals(expectedSessionTime.getSecond(),newSessionTime.getSecond());
    }
    @Test
    void testGet2Patient(){
        Patient p1=new Patient("adam",true);
        Patient p2=new Patient("eva",false);
        tester.getPatientQueue().add(p1);
        assertEquals(1,tester.getPatientQueue());
        DiagnoseDoctor doctor= tester.getAvailable().peek();

        tester.getPatient();

        assertEquals(3,tester.getAvailable().size());
        assertEquals(1,tester.getInuse().size());
        assertEquals(0,tester.getPatientQueue().size());

        assertEquals(p1,doctor.getCurrent());

        LocalDateTime newSessionTime=p1.getSessionTime();
        LocalDateTime expectedSessionTime=LocalDateTime.now().plusSeconds((long) (15/doctor.getTimeMultiplier()));
        assertEquals(expectedSessionTime.getSecond(),newSessionTime.getSecond());
    }
}