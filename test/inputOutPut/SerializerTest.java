package inputOutPut;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {
    Serializer<String> test=new Serializer<>("test/inputOutPut/testString.dat");
    Serializer<Integer> test2=new Serializer<>("test/inputOutPut/testString.dat");
    @Test
    void testWriteReadObjects() {
        List<String> expected=new ArrayList<>();
        expected.add("123123");
        expected.add("yes yess");
        test.writeObjects(expected);
        List<String> result=test.readObjects();
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i),result.get(i));
        }

        expected=new ArrayList<>();
        test.writeObjects(expected);
        result=test.readObjects();
        assertEquals(0, result.size());
    }
    @Test
    void testWriteReadObjectsInteger() {
        List<Integer> expected=new ArrayList<>();
        expected.add(1112);
        expected.add(9988);
        test2.writeObjects(expected);
        List<Integer> result=test2.readObjects();
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i),result.get(i));
        }

        expected=new ArrayList<>();
        test2.writeObjects(expected);
        result=test2.readObjects();
        assertEquals(0, result.size());
    }
}