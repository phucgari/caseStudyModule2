package controler;

import model.doctor.Disease;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiseaseManagerTest {
    DiseaseManager tester=DiseaseManager.getInstance();
    @Test
    void testGetList(){
        List<Disease>list=tester.getList();
        String expected="[Disease{name='Nhổ răng', cureTime=10}, " +
                "Disease{name='Làm răng giả', cureTime=20}, " +
                "Disease{name='Phẫu thuật thẩm mỹ', cureTime=11}, " +
                "Disease{name='Ung thư vú', cureTime=21}, " +
                "Disease{name='Đau họng', cureTime=13}, " +
                "Disease{name='Viêm tai', cureTime=17}, " +
                "Disease{name='Rối loạn cương dương', cureTime=9}, " +
                "Disease{name='Vô sinh', cureTime=19}, " +
                "Disease{name='Gãy tay', cureTime=23}, " +
                "Disease{name='Gãy chân', cureTime=30}]";
        assertEquals(expected,list.toString());
    }
    @Test
    void testGetListArray(){
        List<Disease>[]lists=tester.DiseaseListArray();
        String expected="[[Disease{name='Nhổ răng', cureTime=10}, Disease{name='Làm răng giả', cureTime=20}], " +
                "[Disease{name='Phẫu thuật thẩm mỹ', cureTime=11}, Disease{name='Ung thư vú', cureTime=21}], " +
                "[Disease{name='Đau họng', cureTime=13}, Disease{name='Viêm tai', cureTime=17}], " +
                "[Disease{name='Rối loạn cương dương', cureTime=9}, Disease{name='Vô sinh', cureTime=19}], " +
                "[Disease{name='Gãy tay', cureTime=23}, Disease{name='Gãy chân', cureTime=30}]]";
        assertEquals(expected, Arrays.toString(lists));
    }
    @Test
    void testGetMale(){
        List<Disease>list=tester.getMaleList();
        String expected="[Disease{name='Nhổ răng', cureTime=10}, " +
                "Disease{name='Làm răng giả', cureTime=20}, " +
                "Disease{name='Đau họng', cureTime=13}, " +
                "Disease{name='Viêm tai', cureTime=17}, " +
                "Disease{name='Rối loạn cương dương', cureTime=9}, " +
                "Disease{name='Vô sinh', cureTime=19}, " +
                "Disease{name='Gãy tay', cureTime=23}, " +
                "Disease{name='Gãy chân', cureTime=30}]";
        assertEquals(expected,list.toString());
    }
    @Test
    void testGetFemale(){
        List<Disease>list=tester.getFemaleList();
        String expected="[Disease{name='Nhổ răng', cureTime=10}, " +
                "Disease{name='Làm răng giả', cureTime=20}, " +
                "Disease{name='Phẫu thuật thẩm mỹ', cureTime=11}, " +
                "Disease{name='Ung thư vú', cureTime=21}, " +
                "Disease{name='Đau họng', cureTime=13}, " +
                "Disease{name='Viêm tai', cureTime=17}, " +
                "Disease{name='Gãy tay', cureTime=23}, " +
                "Disease{name='Gãy chân', cureTime=30}]";
        assertEquals(expected,list.toString());
    }
}