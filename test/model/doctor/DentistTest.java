package model.doctor;

import model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class DentistTest {
    Dentist demo1=new Dentist();
    Dentist demo2=new Dentist("test2",2);
    Patient test1=new Patient();
    Patient test2=new Patient();
    Disease disease=new Disease("???",10);
    @BeforeEach
    void init(){
        demo1.setExperience(3);
        demo1.setName("test1");
    }
    @Test
    void testSetGet(){
        assertEquals("test2",demo2.getName());
        assertEquals(2,demo2.getExperience());

        demo2.setExperience(4);
        demo2.setName("test3");
        assertEquals(4,demo2.getExperience());
        assertEquals("test3",demo2.getName());

        assertEquals(3,demo1.getExperience());
        assertEquals("test1",demo1.getName());
    }
    @Test
    void testToString(){
        String result="Dentist name='test1' Experience: Senior\n";
        assertEquals(demo1.toString(),result);
        result="Dentist name='test2' Experience: Junior\n";
        assertEquals(demo2.toString(),result);
    }
    @Test
    void testPatient(){
        demo1.takePatient(test1,disease);
        Queue<Patient> queue=demo1.getPatientQueue();
        assertEquals(queue.remove().getSessionTime().getSecond(), LocalDateTime.now().plusSeconds((long) (10/1.5)).getSecond());

        demo2.takePatient(test2,disease);
        queue= demo2.getPatientQueue();
        assertEquals(queue.remove().getSessionTime().getSecond(), LocalDateTime.now().plusSeconds((long) (10/1.25)).getSecond());
    }
}