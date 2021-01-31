package com.md15.coronavirus.retrofit.provinsi;
// Membuat class lagi bernama Attributes.java karena response dari API tersebut adalah nested objects
public class Attributes {
    private String Provinsi;
    private String Kasus_Posi;
    private String Kasus_Semb;
    private String Kasus_Meni;

    public String getProvinsi() {
        return Provinsi;
    }

    public String getKasus_Posi() {
        return Kasus_Posi;
    }

    public String getKasus_Semb() {
        return Kasus_Semb;
    }

    public String getKasus_Meni() {
        return Kasus_Meni;
    }
}