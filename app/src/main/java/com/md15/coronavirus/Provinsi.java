package com.md15.coronavirus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Provinsi {

    @SerializedName("attributes")
    @Expose
    private Attributes attributes;

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}