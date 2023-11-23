package com.example.glooko.controller;

import com.example.glooko.model.Patient;

public class Controller {
    private  static Patient patient;


    public void createPatient(int age, boolean fast, double level) {
        patient = new Patient(age, fast, level);
    }







    public Controller()
    {
        super();
    }
    public String getPatientRes() {
        return patient.getRes();
    }
}
