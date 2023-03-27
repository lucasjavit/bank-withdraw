package com.lucasit.withdraw.model;


import java.util.Arrays;

public enum TrasactionStatus {

    COMPLETED, FAILED, REFUND, PROGRESS;

    private String status;

    public static TrasactionStatus getStatus(String status) {
        return Arrays.stream(TrasactionStatus.values()).filter(it -> it.name().equals(status)).findFirst().get();
    }
}
