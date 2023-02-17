package controler;

import model.patient.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunnerTest {
    Runner run=new Runner();
    @AfterEach
    void end(){
        HospitalManager.getInstance().flushAll();
    }
    @Test
    void testCheckHealToOut(){
        Patient patient=new Patient("adam",true);
        run.checkHealToOut();
    }
}