package model.doctor;

import model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.PriorityQueue;


import static org.junit.jupiter.api.Assertions.*;

class SurgeonTest {
    Surgeon demo1=new Surgeon();
    Surgeon demo2=new Surgeon("test2",2);
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

        assertEquals("[Disease{name='Gãy tay', cureTime=23}, Disease{name='Gãy chân', cureTime=30}]",demo1.getCurableDisease().toString());
        assertEquals("[Disease{name='Gãy tay', cureTime=23}, Disease{name='Gãy chân', cureTime=30}]",demo2.getCurableDisease().toString());
    }
    @Test
    void testToString(){
        String result="Surgeon name='test1' Experience: Senior\n";
        assertEquals(demo1.toString(),result);
        result="Surgeon name='test2' Experience: Junior\n";
        assertEquals(demo2.toString(),result);
    }
    @Test
    void testPatient(){
        Patient test1=new Patient();
        Patient test2=new Patient();

        test1.setDisease(demo1.getCurableDisease().get(1));
        demo1.takePatient(test1);
        PriorityQueue<Patient> queue=demo1.getPatientQueue();
        assertEquals(queue.peek().getSessionTime().getSecond(), LocalDateTime.now().plusSeconds((long) (30/1.5)).getSecond());

        test2.setDisease(demo1.getCurableDisease().get(0));
        demo1.takePatient(test2);
        LocalDateTime expected=queue.remove().getSessionTime().plusSeconds((long) (23/1.5));
        LocalDateTime result=queue.peek().getSessionTime();
        assertEquals(result.getSecond(), expected.getSecond());
        assertEquals(expected.getSecond(),demo1.getLastPatientTimer().getSecond());

        Patient test3=new Patient();
        test3.setDisease(demo2.getCurableDisease().get(0));
        demo2.takePatient(test3);
        queue= demo2.getPatientQueue();
        result = LocalDateTime.now().plusSeconds((long) (23 / 1.25));
        assertEquals(queue.peek().getSessionTime().getSecond(), result.getSecond());
        assertEquals(demo2.getLastPatientTimer().getSecond(),result.getSecond());    }
}