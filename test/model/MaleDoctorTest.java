package model;

import model.doctor.Dentist;
import model.doctor.MaleDoctor;
import model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class MaleDoctorTest {
    MaleDoctor demo1=new MaleDoctor();
    MaleDoctor demo2=new MaleDoctor("test2",2);
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

        assertEquals("[Disease{name='Rối loạn cương dương', cureTime=9}, Disease{name='Vô sinh', cureTime=19}]",demo1.getCurableDisease().toString());
        assertEquals("[Disease{name='Rối loạn cương dương', cureTime=9}, Disease{name='Vô sinh', cureTime=19}]",demo2.getCurableDisease().toString());
    }
    @Test
    void testToString(){
        String result="MaleDoctor name='test1' Experience: Senior\n";
        assertEquals(demo1.toString(),result);
        result="MaleDoctor name='test2' Experience: Junior\n";
        assertEquals(demo2.toString(),result);
    }
    @Test
    void testPatient(){
        Patient test1=new Patient();
        Patient test2=new Patient();

        demo1.takePatient(test1,1);
        Queue<Patient> queue=demo1.getPatientQueue();
        assertEquals(queue.peek().getSessionTime().getSecond(), LocalDateTime.now().plusSeconds((long) (19/1.5)).getSecond());

        demo2.takePatient(test2,0);
        queue= demo2.getPatientQueue();
        assertEquals(queue.peek().getSessionTime().getSecond(), LocalDateTime.now().plusSeconds((long) (9/1.25)).getSecond());
    }

}