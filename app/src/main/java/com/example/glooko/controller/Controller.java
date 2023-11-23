package com.example.glooko.controller;

import com.example.glooko.model.Patient;
public class Controller {
    // Instance statique de Patient - Attention à l'utilisation de membres statiques
    private static Patient patient;

    // Méthode pour créer une instance de Patient avec les paramètres donnés
    public void createPatient(int age, boolean fast, double level) {
        patient = new Patient(age, fast, level);
    }

    // Constructeur par défaut de la classe Controller
    public Controller() {
        super(); // L'appel à 'super()' n'est pas nécessaire ici, car la classe Controller n'a pas de classe mère explicite
    }

    // Méthode pour récupérer le résultat du patient
    public String getPatientRes() {
        return patient.getRes();
    }
}
