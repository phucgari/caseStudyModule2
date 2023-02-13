package model.doctor;

import model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class OtolaryngologistTest {
    Otolaryngologist demo1=new Otolaryngologist();
    Otolaryngologist demo2=new Otolaryngologist("test2",2);
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

        assertEquals("[Disease{name='Đau họng', cureTime=13}, Disease{name='Viêm tai', cureTime=17}]",demo1.getCurableDisease().toString());
        assertEquals("[Disease{name='Đau họng', cureTime=13}, Disease{name='Viêm tai', cureTime=17}]",demo2.getCurableDisease().toString());
    }
    @Test
    void testToString(){
        String result="Otolaryngologist name='test1' Experience: Senior\n";
        assertEquals(demo1.toString(),result);
        result="Otolaryngologist name='test2' Experience: Junior\n";
        assertEquals(demo2.toString(),result);
    }
    @Test
    void testPatient(){
        Patient test1=new Patient();
        Patient test2=new Patient();

        demo1.takePatient(test1,1);
        Queue<Patient> queue=demo1.getPatientQueue();
        assertEquals(queue.peek().getSessionTime().getSecond(), LocalDateTime.now().plusSeconds((long) (17/1.5)).getSecond());


        demo1.takePatient(test2,0);
        LocalDateTime expected=queue.remove().getSessionTime().plusSeconds((long) (13/1.5));
        LocalDateTime result=queue.peek().getSessionTime();
        assertEquals(result.getSecond(), expected.getSecond());

        Patient test3=new Patient();
        demo2.takePatient(test3,0);
        queue= demo2.getPatientQueue();
        assertEquals(queue.peek().getSessionTime().getSecond(), LocalDateTime.now().plusSeconds((long) (13/1.25)).getSecond());

    }
}