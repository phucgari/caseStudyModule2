package controler;

import model.doctor.DiagnoseDoctor;
import model.doctor.HealingDoctor;
import model.patient.Patient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.PriorityQueue;

public class Runner extends Thread{
    DiagnoseDoctorPool pool=DiagnoseDoctorPool.getInstance();
    DiseaseManager diseaseManager=DiseaseManager.getInstance();
    HealingDoctorManager healingDoctorManager=HealingDoctorManager.getInstance();
    HospitalManager hospitalManager=HospitalManager.getInstance();
    PatientManager patientManager=PatientManager.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public void run() {
        checkQueueToPool();
        checkPoolToHeal();
        checkHealToOut();
        //check add from queue to pool
        //check add from pool to Heal
        //check heal to out
    }

    public void checkHealToOut() {
        hospitalManager.checkPatientInHealer();
    }

    public void checkPoolToHeal() {
        while(true){

        }
    }

    public void checkQueueToPool() {
        PriorityQueue<Patient>patientPriorityQueue=patientManager.getPatientQueue();
        PriorityQueue<DiagnoseDoctor>availableQueue=pool.getAvailable();
        while(!patientPriorityQueue.isEmpty()&&!availableQueue.isEmpty()){
            Patient patient=patientPriorityQueue.peek();
            DiagnoseDoctor doctor=availableQueue.peek();
            pool.getPatient();
            System.out.println(patient+ " started diagnose, finish at " +patient.getSessionTime().format(formatter)+" by "+doctor);
            pool.saveAvailableInuse();
        }
    }
}
