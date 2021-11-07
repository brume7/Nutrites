package com.example.nutrites;

public class UserConClass {
    String ethinicity, mode;

    public UserConClass(){

    }

    public UserConClass(String ethinicity, String mode) {
        this.ethinicity = ethinicity;
        this.mode = mode;
    }

    public String getEthinicity() {
        return ethinicity;
    }

    public void setEthinicity(String ethinicity) {
        this.ethinicity = ethinicity;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
