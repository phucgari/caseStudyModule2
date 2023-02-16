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
        String expected="[Dentist name='Dentist1' Experience: Junior\n" +
                ", Dentist name='Dentist2' Experience: Senior\n" +
                ", Gynecologist name='Gynecologist1' Experience: Leader\n" +
                ", Gynecologist name='Gynecologist2' Experience: Fresher\n" +
                ", Otolaryngologist name='Otolaryngologist1' Experience: Junior\n" +
                ", Otolaryngologist name='Otolaryngologist2' Experience: Junior\n" +
                ", Urologists name='Urologist1' Experience: Fresher\n" +
                ", Urologists name='Urologist2' Experience: Senior\n" +
                ", Surgeon name='Surgeon1' Experience: Senior\n" +
                ", Surgeon name='Surgeon2' Experience: Leader\n" +
                "]";
        assertEquals(expected,tester.getHealingDoctorList().toString());
    }
}