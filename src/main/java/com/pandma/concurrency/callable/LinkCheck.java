package com.pandma.concurrency.callable;

import com.pandma.akka.task.Result;

import java.util.concurrent.Callable;

public interface LinkCheck extends Callable<Result> {

    //boolean performCheck();
}
