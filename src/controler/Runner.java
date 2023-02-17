package controler;

import model.doctor.HealingDoctor;
import model.patient.Patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;

public class Runner extends Thread{
    DiagnoseDoctorPool pool=DiagnoseDoctorPool.getInstance();
    DiseaseManager diseaseManager=DiseaseManager.getInstance();
    HealingDoctorManager healingDoctorManager=HealingDoctorManager.getInstance();
    HospitalManager hospitalManager=HospitalManager.getInstance();
    PatientManager patientManager=PatientManager.getInstance();
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

    }

    public void checkPoolToHeal() {
        while(true){

        }
    }

    public void checkQueueToPool() {
        while(true){

        }
    }
}
