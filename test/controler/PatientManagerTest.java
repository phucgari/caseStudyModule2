package controler;

import model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientManagerTest {
    PatientManager instance=PatientManager.getInstance();
    @BeforeEach
    void init(){
        instance.emptyPatientQueue();
        assertEquals(0,instance.getPatientQueue().size());
    }
    @Test
    void testGetRemove(){
        Patient patient=new Patient("adam",true);
        Patient patient1=new Patient("eva",false);
        instance.addPatientQueue(patient);
        instance.addPatientQueue(patient1);
        assertEquals("Patient{name='adam', gender=male}",instance.removePatientQueue().toString());
        assertEquals("Patient{name='eva', gender=female}",instance.removePatientQueue().toString());
    }
}