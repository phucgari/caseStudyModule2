package model.patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    Patient test1=new Patient();
    Patient test2=new Patient("demo2",false);
    @BeforeEach
    void init(){
        test1.setGender(true);
        test1.setName("demo1");
    }
    @Test
    void testGetSet(){
        assertEquals("demo1",test1.getName());
        assertTrue(test1.getGender());
        assertEquals(LocalDateTime.now().getSecond(),test1.getSessionTime().getSecond());

        assertEquals("demo2",test2.getName());
        assertFalse(test2.getGender());
        assertEquals(LocalDateTime.now().getSecond(),test2.getSessionTime().getSecond());

        test2.setGender(true);
        test2.setName("demo3");
        test2.setSessionTime(LocalDateTime.now().plusSeconds(100));

        assertEquals("demo3",test2.getName());
        assertTrue(test2.getGender());
        assertEquals(LocalDateTime.now().plusSeconds(100).getSecond(),test2.getSessionTime().getSecond());
    }
    @Test
    void testToString(){
        assertEquals("Patient{name='demo1', gender=male}",test1.toString());
        assertEquals("Patient{name='demo2', gender=female}",test2.toString());
    }
}