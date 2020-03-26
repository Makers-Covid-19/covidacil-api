package com.rfbsoft.utils;

public enum ResponseType {
    SUCCES("succes"),
    ERROR("error");

    public String title;


    ResponseType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
