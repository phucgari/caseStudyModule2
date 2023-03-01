package controler;

import inputOutPut.FileReaderWriter;
import model.doctor.Disease;
import model.doctor.HealingDoctor;
import model.patient.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HealingDoctorProcessorTest {
    HealingDoctorProcessor tester= HealingDoctorProcessor.getInstance();
    List<HealingDoctor> healingDoctorList=HealingDoctorManager.getInstance().getHealingDoctorList();
    FileReaderWriter readerWriter=new FileReaderWriter("src/data/sout.txt");
    private String newLine = System.getProperty("line.separator");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @BeforeAll
    public static void before(){
        HospitalManager.getInstance().flushAll();
    }
    @BeforeEach
    public void setUpStreams() {
        readerWriter.delete();
    }
    @AfterEach
    void end(){
        HospitalManager.getInstance().flushAll();
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
        String expected=String.format("|%s|%-40s|%-60s|%-50s|%-60s|%-50s|%-19s|"+newLine, LocalDateTime.now().format(formatter),patient,healer,patient.getDisease(),"out of hospital", "No Disease","");
        assertEquals(expected, readerWriter.read());
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


        String expected=String.format("|%s|%-40s|%-60s|%-50s|%-60s|%-50s|%-19s|"+newLine, LocalDateTime.now().format(formatter),patient,"Dentist name='Dentist2' Experience: Senior",patient.getDisease(),"out of hospital", "No Disease","");
        expected+=String.format("|%s|%-40s|%-60s|%-50s|%-60s|%-50s|%-19s|"+newLine, LocalDateTime.now().format(formatter),"Patient{name='eva', gender=female}",healer,"Disease{name='Vô sinh', cureTime=19}","out of hospital", "No Disease","");
        assertEquals(expected, readerWriter.read());
    }
}