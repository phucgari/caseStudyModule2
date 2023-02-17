package model.patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    Patient test1=new Patient();
    Patient test2=new Patient("demo2",false);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @BeforeEach
    void init(){
        test1.setGender(true);
        test1.setName("demo1");
    }
    @Test
    void testGetSet(){
        assertEquals("demo1",test1.getName());
        assertTrue(test1.getGender());
        assertEquals(LocalDateTime.now().format(formatter),test1.getSessionTime().format(formatter));

        assertEquals("demo2",test2.getName());
        assertFalse(test2.getGender());
        assertEquals(LocalDateTime.now().format(formatter),test2.getSessionTime().format(formatter));

        test2.setGender(true);
        test2.setName("demo3");
        test2.setSessionTime(LocalDateTime.now().plusSeconds(100));

        assertEquals("demo3",test2.getName());
        assertTrue(test2.getGender());
        assertEquals(LocalDateTime.now().plusSeconds(100).format(formatter),test2.getSessionTime().format(formatter));
    }
    @Test
    void testToString(){
        assertEquals("Patient{name='demo1', gender=male}",test1.toString());
        assertEquals("Patient{name='demo2', gender=female}",test2.toString());
    }
}