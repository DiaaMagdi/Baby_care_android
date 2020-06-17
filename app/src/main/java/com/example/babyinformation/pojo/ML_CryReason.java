package com.example.babyinformation.pojo;

public class ML_CryReason {
    private String baby_crying_reason;
    private String operation;

    public ML_CryReason(String baby_crying_reason, String operation) {
        this.baby_crying_reason = baby_crying_reason;
        this.operation = operation;
    }

    public String getBaby_crying_reason() {
        return baby_crying_reason;
    }

    public void setBaby_crying_reason(String baby_crying_reason) {
        this.baby_crying_reason = baby_crying_reason;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
