package com.md15.coronavirus;
// Model class dari API kawal corona untuk menampilkan data covid indonesia
public class Indonesia {

    private String positif;

    private String sembuh;

    private String meninggal;

    private String dirawat;

    //Untuk mengambil value pasien positif
    public String getPositif() {
        return positif;
    }

    //Untuk mengambil value pasien sembuh
    public String getSembuh() {
        return sembuh;
    }

    //Untuk mengambil value pasien meninggal
    public String getMeninggal() {
        return meninggal;
    }

    //Untuk mengambil value pasien dirawat
    public String getDirawat() {
        return dirawat;
    }
}
