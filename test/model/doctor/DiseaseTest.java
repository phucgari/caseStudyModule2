package model.doctor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiseaseTest {
    Disease test1=new Disease();
    Disease test2=new Disease("test2",20);

    @BeforeEach
    void setUp() {
        test1.setName("test1");
        test1.setCureTime(10);
    }
    @Test
    void testSetGet(){
        assertEquals("test1",test1.getName());
        assertEquals(10,test1.getCureTime());

        test1.setName("test3");
        test1.setCureTime(30);
        assertEquals("test3",test1.getName());
        assertEquals(30,test1.getCureTime());

        assertEquals("test2",test2.getName());
        assertEquals(20,test2.getCureTime());
    }
}