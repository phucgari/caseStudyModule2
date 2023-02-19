package model.doctor;

import model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class DentistTest {
    Dentist demo1=new Dentist();
    Dentist demo2=new Dentist("test2",2);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
        String result="Dentist name='test1' Experience: Senior";
        assertEquals(demo1.toString(),result);
        result="Dentist name='test2' Experience: Junior";
        assertEquals(demo2.toString(),result);
    }
    @Test
    void testPatient(){
        Patient test1=new Patient();
        Patient test2=new Patient();

        test1.setDisease(demo1.getCurableDisease().get(1));
        demo1.takePatient(test1);
        PriorityQueue<Patient> queue=demo1.getPatientQueue();
        assertEquals(queue.peek().getSessionTime().format(formatter), LocalDateTime.now().plusSeconds((long) (20/1.5)).format(formatter));

        test2.setDisease(demo2.getCurableDisease().get(0));
        demo1.takePatient(test2);
        LocalDateTime expected=queue.remove().getSessionTime().plusSeconds((long) (10/1.5));
        LocalDateTime result=queue.peek().getSessionTime();
        assertEquals(result.format(formatter), expected.format(formatter));
        assertEquals(expected.format(formatter),demo1.getLastPatientTimer().format(formatter));

        Patient test3=new Patient();
        test3.setDisease(demo1.getCurableDisease().get(0));
        demo2.takePatient(test3);
        queue= demo2.getPatientQueue();
        result = LocalDateTime.now().plusSeconds((long) (10 / 1.25));
        assertEquals(queue.peek().getSessionTime().format(formatter), result.format(formatter));
        assertEquals(demo2.getLastPatientTimer().format(formatter),result.format(formatter));
    }
}