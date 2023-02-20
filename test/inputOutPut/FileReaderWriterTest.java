package inputOutPut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderWriterTest {
    FileReaderWriter readerWriter=new FileReaderWriter("test/inputOutPut/testReadWrite.txt");
    private String newLine = System.getProperty("line.separator");
    @BeforeEach
    void init(){
        readerWriter.delete();
    }
    @Test
    void testReadWrite(){
        readerWriter.write("sayhello"+newLine);
        String result=readerWriter.read();
        assertEquals("sayhello"+newLine,result);
        readerWriter.write("saygoodbye"+newLine);
        result=readerWriter.read();
        assertEquals("sayhello"+newLine+"saygoodbye"+newLine,result);
    }

}