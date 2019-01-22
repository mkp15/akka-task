package com.pandma.concurrency.callable;

import com.pandma.akka.task.Result;
import com.pandma.akka.task.util.Utils;

import java.util.Map;
import java.util.concurrent.Future;

public class PingCheck implements LinkCheck
{
    private Result result;

    public PingCheck(Result result) {
        this.result = result;
    }

    @Override
    public Result call() {
        try {
            Thread.sleep(Utils.getRandomSleep());
            result.setValue("DOWN");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}
