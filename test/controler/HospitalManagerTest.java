package controler;

import model.doctor.Disease;
import model.doctor.HealingDoctor;
import model.patient.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HospitalManagerTest {
    HospitalManager tester=HospitalManager.getInstance();
    @Test
    void testGetDocFromDisease(){
        Disease disease=DiseaseManager.getInstance().getList().get(0);
        HealingDoctor doc=tester.giveDiseaseGetHealingDoc(disease);
        assertEquals(disease.toString(),doc.getCurableDisease().get(0).toString());

        disease=DiseaseManager.getInstance().getList().get(5);
        doc=tester.giveDiseaseGetHealingDoc(disease);
        assertEquals(disease.toString(),doc.getCurableDisease().get(1).toString());

        disease=DiseaseManager.getInstance().getList().get(7);
        HealingDoctor doctor1=HealingDoctorManager.getInstance().getHealingDoctorList().get(6);
        HealingDoctor doctor2=HealingDoctorManager.getInstance().getHealingDoctorList().get(7);
        doctor1.takePatient(new Patient("adam",true),0);
        doctor2.takePatient(new Patient("adam",true),1);
        doc=tester.giveDiseaseGetHealingDoc(disease);
        assertEquals(doctor1,doc);
    }
}