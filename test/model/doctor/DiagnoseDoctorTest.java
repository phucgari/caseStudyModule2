package model.doctor;

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
    }
    @Test
    void testToString(){
        String expected="DiagnoseDoctor name='test1' Experience: Leader\n";
        assertEquals(expected,demo1.toString());
        expected="DiagnoseDoctor name='test2' Experience: Senior\n";
        assertEquals(expected,demo2.toString());
    }
}