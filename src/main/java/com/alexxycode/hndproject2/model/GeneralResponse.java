package com.alexxycode.hndproject2.model;

public record GeneralResponse(
        String clientip,
        String location,
        String greeting
) {
    @Override
    public String toString() {
        return  "client ip : " + clientip +
                "\n+location : " + location + '\n' +
                "greeting : " + greeting;
    }
}
