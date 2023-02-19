package model.doctor;

import model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class UrologistsTest {
    Urologists demo1=new Urologists();
    Urologists demo2=new Urologists("test2",4);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @BeforeEach
    void init(){
        demo1.setExperience(1);
        demo1.setName("test1");
    }
    @Test
    void testSetGet(){
        assertEquals("test2",demo2.getName());
        assertEquals(4,demo2.getExperience());

        demo2.setExperience(4);
        demo2.setName("test3");
        assertEquals(4,demo2.getExperience());
        assertEquals("test3",demo2.getName());

        assertEquals(1,demo1.getExperience());
        assertEquals("test1",demo1.getName());

        assertEquals("[Disease{name='Rối loạn cương dương', cureTime=9}, Disease{name='Vô sinh', cureTime=19}]",demo1.getCurableDisease().toString());
        assertEquals("[Disease{name='Rối loạn cương dương', cureTime=9}, Disease{name='Vô sinh', cureTime=19}]",demo2.getCurableDisease().toString());
    }
    @Test
    void testToString(){
        String result="Urologists name='test1' Experience: Fresher";
        assertEquals(demo1.toString(),result);
        result="Urologists name='test2' Experience: Leader";
        assertEquals(demo2.toString(),result);
    }
    @Test
    void testPatient(){
        Patient test1=new Patient();
        Patient test2=new Patient();

        test1.setDisease(demo1.getCurableDisease().get(1));
        demo1.takePatient(test1);
        PriorityQueue<Patient> queue=demo1.getPatientQueue();
        assertEquals(queue.peek().getSessionTime().format(formatter), LocalDateTime.now().plusSeconds((long) (19)).format(formatter));

        test2.setDisease(demo1.getCurableDisease().get(0));
        demo1.takePatient(test2);
        LocalDateTime expected=queue.remove().getSessionTime().plusSeconds(9);
        LocalDateTime result=queue.peek().getSessionTime();
        assertEquals(result.format(formatter), expected.format(formatter));
        assertEquals(expected.format(formatter),demo1.getLastPatientTimer().format(formatter));

        Patient test3=new Patient();
        test3.setDisease(demo2.getCurableDisease().get(0));
        demo2.takePatient(test3);
        queue= demo2.getPatientQueue();
        result = LocalDateTime.now().plusSeconds(9 /2);
        assertEquals(queue.peek().getSessionTime().format(formatter), result.format(formatter));
        assertEquals(demo2.getLastPatientTimer().format(formatter),result.format(formatter));
    }

}