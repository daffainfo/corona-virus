package com.md15.coronavirus.retrofit.global;
// Model class dari API kawal corona untuk menampilkan data covid per negara
// Membuat 2 class karena response dari API tersebut adalah nested objects
public class Global {
    private Attributes attributes;

    public Attributes getAttributes() {
        return attributes;
    }
}