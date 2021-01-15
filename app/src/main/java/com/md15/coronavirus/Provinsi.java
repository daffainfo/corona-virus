package com.md15.coronavirus;
// Model class dari API kawal corona untuk menampilkan data covid per provinsi
// Membuat 2 class karena response dari API tersebut adalah nested objects
public class Provinsi {

    private Attributes attributes;

    public Attributes getAttributes() {
        return attributes;
    }
}

class Attributes {
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