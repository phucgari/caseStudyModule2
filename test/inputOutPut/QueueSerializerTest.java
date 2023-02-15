package inputOutPut;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;


import static org.junit.jupiter.api.Assertions.*;

class QueueSerializerTest {
    QueueSerializer<String> test=new QueueSerializer<>("test/inputOutPut/testQueueString.dat");
    QueueSerializer<Integer> test2=new QueueSerializer<>("test/inputOutPut/testQueueInteger.dat");
    @Test
    void testWriteReadObjects() {
        PriorityQueue<String> expected=new PriorityQueue<>();
        expected.add("123123");
        expected.add("yes yess");
        test.writeObjects(expected);
        PriorityQueue<String> result=test.readObjects();
        int size = expected.size();
        for (int i = 0; i < size; i++) {
            assertEquals(expected.remove(),result.remove());
        }

        expected=new PriorityQueue<>();
        test.writeObjects(expected);
        result=test.readObjects();
        assertEquals(0, result.size());
    }
    @Test
    void testWriteReadObjectsInteger() {
        PriorityQueue<Integer> expected=new PriorityQueue<>();
        expected.add(1112);
        expected.add(9988);
        test2.writeObjects(expected);
        PriorityQueue<Integer> result=test2.readObjects();
        int size = expected.size();
        for (int i = 0; i < size; i++) {
            assertEquals(expected.remove(),result.remove());
        }

        expected=new PriorityQueue<>();
        test2.writeObjects(expected);
        result=test2.readObjects();
        assertEquals(0, result.size());
    }
}