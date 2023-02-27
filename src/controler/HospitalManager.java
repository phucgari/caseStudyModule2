package controler;

import inputOutPut.FileReaderWriter;
import model.doctor.*;
import model.patient.Patient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HospitalManager extends Thread{
    private static HospitalManager instance;
    private final DiagnoseDoctorPool pool=DiagnoseDoctorPool.getInstance();
    private final HealingDoctorProcessor healingDoctorProcessor = HealingDoctorProcessor.getInstance();
    private final PatientManager patientManager=PatientManager.getInstance();
    private final HealingDoctorManager healingDoctorManager=HealingDoctorManager.getInstance();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private boolean onOff=true;
    private String newLine = System.getProperty("line.separator");
    FileReaderWriter fileReaderWriter=new FileReaderWriter("src/data/sout.txt");

    private HospitalManager() {}

    public static HospitalManager getInstance() {
        if(instance==null)instance=new HospitalManager();
        return instance;
    }

    public void flushAll(){
        DiagnoseDoctorPool.getInstance().flushAvailableInuse();
        PatientManager.getInstance().emptyPatientQueue();
        HealingDoctorManager.getInstance().flushHealingDoctorManager();
        DiagnoseDoctor doctor1=new DiagnoseDoctor("Diag1",1);
        DiagnoseDoctor doctor2=new DiagnoseDoctor("Diag2",2);
        DiagnoseDoctor doctor3=new DiagnoseDoctor("Diag3",3);
        DiagnoseDoctor doctor4=new DiagnoseDoctor("Diag4",4);
        DiagnoseDoctor doctor5=new DiagnoseDoctor("Diag5",2);
        List<DiagnoseDoctor> doctors=new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor5);
        doctors.add(doctor2);
        doctors.add(doctor3);
        doctors.add(doctor4);
        DiagnoseDoctorPool.DIAGNOSE_DOCTOR_LIST.writeObjects(doctors);

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
        healingDoctorProcessor.checkPatientInHealer();
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
