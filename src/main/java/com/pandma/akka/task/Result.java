package com.pandma.akka.task;

public class Result {
    private volatile String value;

    public Result(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isDown(){
        return value.equals("DOWN");
    }

    @Override
    public String toString() {
        return "Result{" +
                "value='" + value + '\'' +
                '}';
    }
}
