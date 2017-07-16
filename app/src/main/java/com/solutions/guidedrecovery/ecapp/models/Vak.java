package com.solutions.guidedrecovery.ecapp.models;

/**
 * Created by Dipak on 21-6-2017.
 */

public class Vak {


    private String moduleCode;
    private String moduleNaam;
    private double cijfer;
    private int ec;


    public Vak(String moduleCode, String moduleNaam, double cijfer, int ec){

        this.cijfer = cijfer;
        this.moduleCode = moduleCode;
        this.moduleNaam = moduleNaam;
        this.ec = ec;

    }

    public String getModuleCode(){
        return moduleCode;
    }

    public void setModuleCode(String moduleCode){
        this.moduleCode = moduleCode;
    }

    public String getModuleNaam(){
        return moduleNaam;
    }

    public void setModuleNaam(String moduleNaam){
        this.moduleNaam = moduleNaam;
    }

    public double getCijfer(){
        return cijfer;
    }

    public void setCijfer(double cijfer){
        this.cijfer = cijfer;
    }

    public int getEc(){
        return ec;
    }

    public void setEc(int ec){
        this.ec = ec;
    }

}
