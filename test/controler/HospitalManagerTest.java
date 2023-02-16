package controler;

import model.doctor.Disease;
import model.doctor.HealingDoctor;
import model.doctor.Urologists;
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
        Patient patient1=new Patient("adam",true);
        patient1.setDisease(doctor2.getCurableDisease().get(0));
        Patient patient2=new Patient("adam1",true);
        patient2.setDisease(doctor2.getCurableDisease().get(1));
        doctor1.takePatient(patient1);
        doctor2.takePatient(patient2);
        doc=tester.giveDiseaseGetHealingDoc(disease);
        assertEquals(doctor1.toString(),doc.toString());
    }
}