package com.md15.coronavirus.retrofit;
// Model class dari API kawal corona untuk menampilkan data covid indonesia
public class Indonesia {
    private String positif;
    private String sembuh;
    private String meninggal;
    private String dirawat;

    public String getPositif() {
        return positif;
    }

    public String getSembuh() {
        return sembuh;
    }

    public String getMeninggal() {
        return meninggal;
    }

    public String getDirawat() {
        return dirawat;
    }
}
