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

        assertEquals("[Disease{name='Nhổ răng', cureTime=10}, Disease{name='Làm răng giả', cureTime=20}]",demo1.getCurableDisease().toString());
        assertEquals("[Disease{name='Nhổ răng', cureTime=10}, Disease{name='Làm răng giả', cureTime=20}]",demo2.getCurableDisease().toString());
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
        Patient test1=new Patient();
        Patient test2=new Patient();

        demo1.takePatient(test1,1);
        Queue<Patient> queue=demo1.getPatientQueue();
        assertEquals(queue.peek().getSessionTime().getSecond(), LocalDateTime.now().plusSeconds((long) (20/1.5)).getSecond());

        demo2.takePatient(test2,0);
        queue= demo2.getPatientQueue();
        assertEquals(queue.peek().getSessionTime().getSecond(), LocalDateTime.now().plusSeconds((long) (10/1.25)).getSecond());
    }
}