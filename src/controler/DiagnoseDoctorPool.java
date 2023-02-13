package controler;

import model.doctor.DiagnoseDoctor;

import java.util.LinkedList;
import java.util.Queue;

// design pattern Object pool
public class DiagnoseDoctorPool {
    private static final int NUMBER_OF_DIAGNOSE_DOCTOR=4;
    private final Queue<DiagnoseDoctor> available= new LinkedList<>();
    private final Queue<DiagnoseDoctor> inuse= new LinkedList<>();
}
