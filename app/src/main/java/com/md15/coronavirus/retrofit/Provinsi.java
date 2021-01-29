package com.md15.coronavirus.retrofit;
// Model class dari API kawal corona untuk menampilkan data covid per provinsi
// Membuat 2 class karena response dari API tersebut adalah nested objects
public class Provinsi {

    private AttributesIndo attributesIndo;

    public AttributesIndo getAttributes() {
        return attributesIndo;
    }
}