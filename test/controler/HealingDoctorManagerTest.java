package controler;

import inputOutPut.Serializer;
import model.doctor.HealingDoctor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealingDoctorManagerTest {
    HealingDoctorManager tester=HealingDoctorManager.getInstance();

    @AfterEach
    void after(){
        HospitalManager.getInstance().flushAll();
    }
    @Test
    void testToStringList(){
        String expected="[Dentist name='Dentist1' Experience: Junior" +
                ", Dentist name='Dentist2' Experience: Senior" +
                ", Gynecologist name='Gynecologist1' Experience: Leader" +
                ", Gynecologist name='Gynecologist2' Experience: Fresher" +
                ", Otolaryngologist name='Otolaryngologist1' Experience: Junior" +
                ", Otolaryngologist name='Otolaryngologist2' Experience: Junior" +
                ", Urologist name='Urologist1' Experience: Fresher" +
                ", Urologist name='Urologist2' Experience: Senior" +
                ", Surgeon name='Surgeon1' Experience: Senior" +
                ", Surgeon name='Surgeon2' Experience: Leader" +
                "]";
        assertEquals(expected,tester.getHealingDoctorList().toString());
    }
    @Test
    void testAdderDentist(){
        String expected="[Dentist name='Dentist1' Experience: Junior, " +
                "Dentist name='Dentist2' Experience: Senior, " +
                "Gynecologist name='Gynecologist1' Experience: Leader, " +
                "Gynecologist name='Gynecologist2' Experience: Fresher, " +
                "Otolaryngologist name='Otolaryngologist1' Experience: Junior, " +
                "Otolaryngologist name='Otolaryngologist2' Experience: Junior, " +
                "Urologist name='Urologist1' Experience: Fresher, " +
                "Urologist name='Urologist2' Experience: Senior, " +
                "Surgeon name='Surgeon1' Experience: Senior, " +
                "Surgeon name='Surgeon2' Experience: Leader, " +
                "Dentist name='phuc' Experience: Fresher]";
        tester.addDentist("phuc",1);
        assertEquals(expected,tester.getHealingDoctorList().toString());
        assertEquals(expected,HealingDoctorManager.serializer.readObjects().toString());
    }
    @Test
    void testAdderGynecologist(){
        String expected="[Dentist name='Dentist1' Experience: Junior, " +
                "Dentist name='Dentist2' Experience: Senior, " +
                "Gynecologist name='Gynecologist1' Experience: Leader, " +
                "Gynecologist name='Gynecologist2' Experience: Fresher, " +
                "Otolaryngologist name='Otolaryngologist1' Experience: Junior, " +
                "Otolaryngologist name='Otolaryngologist2' Experience: Junior, " +
                "Urologist name='Urologist1' Experience: Fresher, " +
                "Urologist name='Urologist2' Experience: Senior, " +
                "Surgeon name='Surgeon1' Experience: Senior, " +
                "Surgeon name='Surgeon2' Experience: Leader, " +
                "Gynecologist name='phuc' Experience: Fresher]";
        tester.addGynecologist("phuc",1);
        assertEquals(expected,tester.getHealingDoctorList().toString());
        assertEquals(expected,HealingDoctorManager.serializer.readObjects().toString());
    }
    @Test
    void testAdderOtolaryngologist(){
        String expected="[Dentist name='Dentist1' Experience: Junior, " +
                "Dentist name='Dentist2' Experience: Senior, " +
                "Gynecologist name='Gynecologist1' Experience: Leader, " +
                "Gynecologist name='Gynecologist2' Experience: Fresher, " +
                "Otolaryngologist name='Otolaryngologist1' Experience: Junior, " +
                "Otolaryngologist name='Otolaryngologist2' Experience: Junior, " +
                "Urologist name='Urologist1' Experience: Fresher, " +
                "Urologist name='Urologist2' Experience: Senior, " +
                "Surgeon name='Surgeon1' Experience: Senior, " +
                "Surgeon name='Surgeon2' Experience: Leader, " +
                "Otolaryngologist name='phuc' Experience: Fresher]";
        tester.addOtolaryngologist("phuc",1);
        assertEquals(expected,tester.getHealingDoctorList().toString());
        assertEquals(expected,HealingDoctorManager.serializer.readObjects().toString());
    }
    @Test
    void testAdderUrologists(){
        String expected="[Dentist name='Dentist1' Experience: Junior, " +
                "Dentist name='Dentist2' Experience: Senior, " +
                "Gynecologist name='Gynecologist1' Experience: Leader, " +
                "Gynecologist name='Gynecologist2' Experience: Fresher, " +
                "Otolaryngologist name='Otolaryngologist1' Experience: Junior, " +
                "Otolaryngologist name='Otolaryngologist2' Experience: Junior, " +
                "Urologist name='Urologist1' Experience: Fresher, " +
                "Urologist name='Urologist2' Experience: Senior, " +
                "Surgeon name='Surgeon1' Experience: Senior, " +
                "Surgeon name='Surgeon2' Experience: Leader, " +
                "Urologist name='phuc' Experience: Fresher]";
        tester.addUrologist("phuc",1);
        assertEquals(expected,tester.getHealingDoctorList().toString());
        assertEquals(expected,HealingDoctorManager.serializer.readObjects().toString());
    }
    @Test
    void testAdderSurgeon(){
        String expected="[Dentist name='Dentist1' Experience: Junior, " +
                "Dentist name='Dentist2' Experience: Senior, " +
                "Gynecologist name='Gynecologist1' Experience: Leader, " +
                "Gynecologist name='Gynecologist2' Experience: Fresher, " +
                "Otolaryngologist name='Otolaryngologist1' Experience: Junior, " +
                "Otolaryngologist name='Otolaryngologist2' Experience: Junior, " +
                "Urologist name='Urologist1' Experience: Fresher, " +
                "Urologist name='Urologist2' Experience: Senior, " +
                "Surgeon name='Surgeon1' Experience: Senior, " +
                "Surgeon name='Surgeon2' Experience: Leader, " +
                "Surgeon name='phuc' Experience: Fresher]";
        tester.addSurgeon("phuc",1);
        assertEquals(expected,tester.getHealingDoctorList().toString());
        assertEquals(expected,HealingDoctorManager.serializer.readObjects().toString());
    }
    @Test
    void testRemover(){
        String expected="[Dentist name='Dentist2' Experience: Senior" +
                ", Gynecologist name='Gynecologist1' Experience: Leader" +
                ", Gynecologist name='Gynecologist2' Experience: Fresher" +
                ", Otolaryngologist name='Otolaryngologist1' Experience: Junior" +
                ", Otolaryngologist name='Otolaryngologist2' Experience: Junior" +
                ", Urologist name='Urologist1' Experience: Fresher" +
                ", Urologist name='Urologist2' Experience: Senior" +
                ", Surgeon name='Surgeon1' Experience: Senior" +
                ", Surgeon name='Surgeon2' Experience: Leader" +
                "]";
        tester.remove(0);
        assertEquals(expected,tester.getHealingDoctorList().toString());
        assertEquals(expected,HealingDoctorManager.serializer.readObjects().toString());
        expected="[Dentist name='Dentist2' Experience: Senior" +
                ", Gynecologist name='Gynecologist2' Experience: Fresher" +
                ", Otolaryngologist name='Otolaryngologist1' Experience: Junior" +
                ", Otolaryngologist name='Otolaryngologist2' Experience: Junior" +
                ", Urologist name='Urologist1' Experience: Fresher" +
                ", Urologist name='Urologist2' Experience: Senior" +
                ", Surgeon name='Surgeon1' Experience: Senior" +
                ", Surgeon name='Surgeon2' Experience: Leader" +
                "]";
        tester.remove(1);
        assertEquals(expected,tester.getHealingDoctorList().toString());
        assertEquals(expected,HealingDoctorManager.serializer.readObjects().toString());
    }
}