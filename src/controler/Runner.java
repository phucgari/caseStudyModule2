package controler;

import inputOutPut.FileReaderWriter;
import model.doctor.DiagnoseDoctor;
import model.doctor.Disease;
import model.patient.Patient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;

public class Runner extends Thread{
    private final DiagnoseDoctorPool pool=DiagnoseDoctorPool.getInstance();
    private final HospitalManager hospitalManager=HospitalManager.getInstance();
    private final PatientManager patientManager=PatientManager.getInstance();
    private final HealingDoctorManager healingDoctorManager=HealingDoctorManager.getInstance();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private boolean onOff=true;
    private String newLine = System.getProperty("line.separator");
    FileReaderWriter fileReaderWriter=new FileReaderWriter("src/data/sout.txt");

    public Runner() {}

    @Override
    public void run() {
        while (onOff) {
            checkQueueToPool();
            checkPoolToHeal();
            checkHealToOut();
            try {
                Thread.sleep(950);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void switchOnOff(){
        onOff=!onOff;
    }

    public void checkHealToOut() {
        hospitalManager.checkPatientInHealer();
    }

    public void checkPoolToHeal() {
        LocalDateTime now=LocalDateTime.now();
        PriorityQueue<DiagnoseDoctor>inuseQueue=pool.getInuse();
        if(inuseQueue.isEmpty())return;
        while(inuseQueue.peek().getCurrent().getSessionTime().isBefore(now)){
            pool.releasePatient();
            if(inuseQueue.isEmpty())break;
        }
        pool.saveAvailableInuse();
        healingDoctorManager.serializeList();
    }

    public void checkQueueToPool() {
        PriorityQueue<Patient>patientPriorityQueue=patientManager.getPatientQueue();
        PriorityQueue<DiagnoseDoctor>availableQueue=pool.getAvailable();
        while(!patientPriorityQueue.isEmpty()&&!availableQueue.isEmpty()){
            Patient patient=patientPriorityQueue.peek();
            DiagnoseDoctor doctor=availableQueue.peek();
            pool.getPatient();
            String str=String.format("%s: %-40s go from %-60s with %-50s to %-60s with %-50s newSessionTime %-50s"+newLine, LocalDateTime.now().format(formatter),patient,"Queue","No Disease",doctor,"No Disease",patient.getSessionTime().format(formatter));
            fileReaderWriter.write(str);
            pool.saveAvailableInuse();
        }
    }
}
