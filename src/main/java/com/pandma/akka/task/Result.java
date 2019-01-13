package com.pandma.akka.task;

public class Result {
    private String value;

    public Result(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Result{" +
                "value='" + value + '\'' +
                '}';
    }
}
