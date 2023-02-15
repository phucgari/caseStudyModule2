package model.doctor;

import model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class GynecologistTest {
    Gynecologist demo1=new Gynecologist();
    Gynecologist demo2=new Gynecologist("test2",3);
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

        assertEquals("[Disease{name='Phẫu thuật thẩm mỹ', cureTime=11}, Disease{name='Ung thư vú', cureTime=21}]",demo1.getCurableDisease().toString());
        assertEquals("[Disease{name='Phẫu thuật thẩm mỹ', cureTime=11}, Disease{name='Ung thư vú', cureTime=21}]",demo2.getCurableDisease().toString());
    }
    @Test
    void testToString(){
        String expected="Gynecologist name='test1' Experience: Leader\n";
        assertEquals(expected,demo1.toString());
        expected="Gynecologist name='test2' Experience: Senior\n";
        assertEquals(expected,demo2.toString());
    }
    @Test
    void testTakePatient(){
        Patient test1=new Patient();
        Patient test2=new Patient();

        demo1.takePatient(test1,1);
        PriorityQueue<Patient> queue=demo1.getPatientQueue();
        assertEquals(queue.peek().getSessionTime().getSecond(), LocalDateTime.now().plusSeconds(21/2).getSecond());


        demo1.takePatient(test2,0);
        LocalDateTime expected=queue.remove().getSessionTime().plusSeconds((long) (11/2));
        LocalDateTime result=queue.peek().getSessionTime();
        assertEquals(result.getSecond(), expected.getSecond());

        Patient test3=new Patient();
        demo2.takePatient(test3,0);
        queue= demo2.getPatientQueue();
        assertEquals(queue.peek().getSessionTime().getSecond(), LocalDateTime.now().plusSeconds((long) (11/1.5)).getSecond());

    }
}