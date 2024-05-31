package com.example.ex17custom_queries02;

public enum Status {

    ONTIME, DELAYED, CANCELLED;

    public static Status convertFrom(String string) {
        String input = string.trim().toUpperCase();
        Status status = null;
        switch (input) {
            case "ONTIME":
                status = Status.ONTIME;
                break;
            case "DELAYED":
                status = Status.DELAYED;
                break;
            case "CANCELLED":
                status = Status.CANCELLED;
                break;
            default:
                break;
        }
        return status;
    }
}
