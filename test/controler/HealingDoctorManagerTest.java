package controler;

import inputOutPut.Serializer;
import model.doctor.HealingDoctor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealingDoctorManagerTest {
    HealingDoctorManager tester=HealingDoctorManager.getInstance();
    Serializer<HealingDoctor>serializer=new Serializer<>("src/data/healingDoctorList.dat");
    @Test
    void testToStringList(){
        assertEquals(serializer.readObjects().toString(),tester.getHealingDoctorList().toString());
    }
}