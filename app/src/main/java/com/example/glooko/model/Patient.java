package com.example.glooko.model;

public class Patient {

    private  int age;
    private  boolean fast;
    private double level;
    private static String res;
    public int getAge() {
        return age;
    }

    public boolean isFast() {
        return fast;
    }

    public double getLevel() {
        return level;
    }

    public String getRes() {
        return res;
    }

    public Patient(int age, boolean fast, double level) {
        this.age = age;
        this.fast = fast;
        this.level = level;
        calcu();
    }

    private  void calcu(){

        String normal= "The level of blood sugar is normal.";
        String high= "The blood sugar level is too high." ;
        String low= "The blood sugar level is too low." ;

        if (fast) {
            if (age < 120 && age >= 13) {
                if (level >= 5.0 && level <= 7.2) {
                    res=normal;
                } else if (level > 7.2) {
                    res=high;

                } else if (level < 5.0) {

                    res=low;

                }
            } else if (age < 13 && age > 6) {
                if (level >= 5.0 && level <= 10.0) {
                    res=normal;
                } else if (level > 10.0) {
                    res=high;

                } else if (level < 5.0) {

                    res=low;

                }

            } else if (age < 6 && age > 1) {
                if (level >= 5.5 && level <= 10.0) {
                    res=normal;
                } else if (level > 10.0) {
                    res=high;

                } else if (level < 5.5) {

                    res=low;

                }

            }


        } else if (!fast) {
            if (age > 6 && level < 10.5) {
                res=normal;
            } else {
                res=high;
            }

        }

    }






}
