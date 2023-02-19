package controler;

import model.doctor.Disease;
import model.doctor.HealingDoctor;
import model.doctor.Urologists;
import model.patient.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HospitalManagerTest {
    HospitalManager tester=HospitalManager.getInstance();
    List<HealingDoctor> healingDoctorList=HealingDoctorManager.getInstance().getHealingDoctorList();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private String newLine = System.getProperty("line.separator");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    void end(){
        HospitalManager.getInstance().flushAll();
        System.setOut(originalOut);
    }
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
    @Test
    void testCheckPatientInHealer(){
        HealingDoctor healer=healingDoctorList.get(0);

        Patient patient=new Patient("adam",true);
        patient.setSessionTime(LocalDateTime.now().minusSeconds(200));
        patient.setDisease(healer.getCurableDisease().get(1));
        healer.takePatient(patient);

        tester.checkPatientInHealer();
        String expected=String.format("%s: %-40s go from %-60s with %-50s to %-60s with %-50s at SessionTime %-50s"+newLine, LocalDateTime.now().format(formatter),patient,healer,patient.getDisease(),"out of hospital", "No Disease",patient.getSessionTime().format(formatter));
        assertEquals(expected, outContent.toString());
    }
    @Test
    void testCheck2PatientInHealer(){
        HealingDoctor healer=healingDoctorList.get(1);

        Patient patient=new Patient("adam",true);
        patient.setSessionTime(LocalDateTime.now().minusSeconds(2000));
        patient.setDisease(healer.getCurableDisease().get(1));
        healer.takePatient(patient);

        tester.checkPatientInHealer();


        healer=healingDoctorList.get(7);
        Patient patient2=new Patient("eva",false);
        patient2.setSessionTime(LocalDateTime.now().minusSeconds(100));
        patient2.setDisease(healer.getCurableDisease().get(1));
        healer.takePatient(patient2);

        tester.checkPatientInHealer();


        String expected=String.format("%s: %-40s go from %-60s with %-50s to %-60s with %-50s at SessionTime %-50s"+newLine, LocalDateTime.now().format(formatter),patient,"Dentist name='Dentist2' Experience: Senior",patient.getDisease(),"out of hospital", "No Disease",patient.getSessionTime().format(formatter));
        expected+=String.format("%s: %-40s go from %-60s with %-50s to %-60s with %-50s at SessionTime %-50s"+newLine, LocalDateTime.now().format(formatter),"Patient{name='eva', gender=female}",healer,"Disease{name='Vô sinh', cureTime=19}","out of hospital", "No Disease",patient2.getSessionTime().format(formatter));
        assertEquals(expected, outContent.toString());
    }
}