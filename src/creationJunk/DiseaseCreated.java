package creationJunk;

import model.doctor.Dentist;
import model.doctor.Disease;
import model.doctor.Surgeon;
import model.doctor.Gynecologist;
import model.doctor.Urologist;
import model.doctor.Otolaryngologist;

import java.util.ArrayList;
import java.util.List;

public class DiseaseCreated {
    public static void main(String[] args) {
        List<Disease> arrayList=new ArrayList<>();
        arrayList.add(new Disease("Nhổ răng",10));
        arrayList.add(new Disease("Làm răng giả",20));
        Dentist.DISEASE_SERIALIZER.writeObjects(arrayList);

        List<Disease> arrayList1=new ArrayList<>();
        arrayList1.add(new Disease("Phẫu thuật thẩm mỹ",11));
        arrayList1.add(new Disease("Ung thư vú",21));
        Gynecologist.DISEASE_SERIALIZER.writeObjects(arrayList1);

        List<Disease> arrayList2=new ArrayList<>();
        arrayList2.add(new Disease("Rối loạn cương dương",9));
        arrayList2.add(new Disease("Vô sinh",19));
        Urologist.DISEASE_SERIALIZER.writeObjects(arrayList2);

        List<Disease> arrayList3=new ArrayList<>();
        arrayList3.add(new Disease("Đau họng",13));
        arrayList3.add(new Disease("Viêm tai",17));
        Otolaryngologist.DISEASE_SERIALIZER.writeObjects(arrayList3);

        List<Disease> arrayList4=new ArrayList<>();
        arrayList4.add(new Disease("Gãy tay",23));
        arrayList4.add(new Disease("Gãy chân",30));
        Surgeon.DISEASE_SERIALIZER.writeObjects(arrayList4);
    }
}