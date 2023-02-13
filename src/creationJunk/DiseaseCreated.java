package creationJunk;

import model.doctor.Disease;
import model.doctor.Surgeon;

import java.util.ArrayList;
import java.util.List;

public class DiseaseCreated {
//    public static void main(String[] args) {
//        List<Disease> arrayList=new ArrayList<>();
//        arrayList.add(new Disease("Nhổ răng",10));
//        arrayList.add(new Disease("Làm răng giả",20));
//        Dentist.DISEASE_SERIALIZER.writeObjects(arrayList);
//    }
//
//    public static void main(String[] args) {
//        List<Disease> arrayList=new ArrayList<>();
//        arrayList.add(new Disease("Phẫu thuật thẩm mỹ",11));
//        arrayList.add(new Disease("Ung thư vú",21));
//        Gynecologist.DISEASE_SERIALIZER.writeObjects(arrayList);
//    }
//    public static void main(String[] args) {
//        List<Disease> arrayList=new ArrayList<>();
//        arrayList.add(new Disease("Rối loạn cương dương",9));
//        arrayList.add(new Disease("Vô sinh",19));
//        Urologists.DISEASE_SERIALIZER.writeObjects(arrayList);
//    }
//    public static void main(String[] args) {
//        List<Disease> arrayList=new ArrayList<>();
//        arrayList.add(new Disease("Đau họng",13));
//        arrayList.add(new Disease("Viêm tai",17));
//        Otolaryngologist.DISEASE_SERIALIZER.writeObjects(arrayList);
//    }
        public static void main(String[] args) {
        List<Disease> arrayList=new ArrayList<>();
        arrayList.add(new Disease("Gãy tay",23));
        arrayList.add(new Disease("Gãy chân",30));
        Surgeon.DISEASE_SERIALIZER.writeObjects(arrayList);
    }
}