package com.alexxycode.hndproject2.model;

public record GenaralResponse(
        String clientip,
        String location,
        String greeting
) {
    @Override
    public String toString() {
        return  "client ip : " + clientip + '\'' +
                "location : " + location + '\'' +
                "greeting : " + greeting;
    }
}
