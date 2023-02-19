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
        String expected="[Dentist name='Dentist1' Experience: Junior" +
                ", Dentist name='Dentist2' Experience: Senior" +
                ", Gynecologist name='Gynecologist1' Experience: Leader" +
                ", Gynecologist name='Gynecologist2' Experience: Fresher" +
                ", Otolaryngologist name='Otolaryngologist1' Experience: Junior" +
                ", Otolaryngologist name='Otolaryngologist2' Experience: Junior" +
                ", Urologists name='Urologist1' Experience: Fresher" +
                ", Urologists name='Urologist2' Experience: Senior" +
                ", Surgeon name='Surgeon1' Experience: Senior" +
                ", Surgeon name='Surgeon2' Experience: Leader" +
                "]";
        assertEquals(expected,tester.getHealingDoctorList().toString());
    }
}