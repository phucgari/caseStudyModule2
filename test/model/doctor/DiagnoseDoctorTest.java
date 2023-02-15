package model.doctor;

import model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiagnoseDoctorTest {
    DiagnoseDoctor demo1=new DiagnoseDoctor();
    DiagnoseDoctor demo2=new DiagnoseDoctor("test2",3);
    @BeforeEach
    void init(){
        demo1.setName("test1");
        demo1.setExperience(4);
    }
    @Test
    void testSetGet(){
        assertEquals("test2",demo2.getName());
        assertEquals(3,demo2.getExperience());

        demo2.setName("test3");
        demo2.setExperience(2);
        assertEquals("test3",demo2.getName());
        assertEquals(2,demo2.getExperience());

        assertEquals("test1",demo1.getName());
        assertEquals(4,demo1.getExperience());

        Patient p1=new Patient("Adam",true);
        Patient p2=new Patient("Eva",false);
        demo1.setCurrent(p1);
        assertEquals("Patient{name='Adam', gender=male}",p1.toString());
        demo2.setCurrent(p2);
        assertEquals("Patient{name='Eva', gender=female}",p2.toString());
    }
    @Test
    void testToString(){
        String expected="DiagnoseDoctor name='test1' Experience: Leader\n";
        assertEquals(expected,demo1.toString());
        expected="DiagnoseDoctor name='test2' Experience: Senior\n";
        assertEquals(expected,demo2.toString());
    }
    @Test
    void testComparable(){
        Patient p1=new Patient();
        Patient p2=new Patient();
        p1.setSessionTime(p1.getSessionTime().plusSeconds(1));
        demo1.setCurrent(p1);
        demo2.setCurrent(p2);
        assertTrue(demo1.compareTo(demo2)>0);
        p1.setSessionTime(p1.getSessionTime().minusSeconds(2));
        assertTrue(demo1.compareTo(demo2)<0);
        p2.setSessionTime(p2.getSessionTime().minusSeconds(2));
        assertTrue(demo1.compareTo(demo2)>0);
    }
}