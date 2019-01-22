package com.pandma.concurrency;

import com.pandma.akka.task.Result;
import com.pandma.concurrency.callable.PingCheck;
import com.pandma.concurrency.callable.SnmpGetCheck;
import com.pandma.concurrency.callable.UrlCheck;
import com.sun.webpane.webkit.network.URLLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LinkCheckRunner {

    static ExecutorService executorService = Executors.newFixedThreadPool(3);
    public static void main(String[] args) {
        Result result = new Result("DOWN");
        List<Future<Result>> futures = new ArrayList<>();
        futures.add(executorService.submit(new SnmpGetCheck(result)));
        futures.add(executorService.submit(new UrlCheck(result)));
        futures.add(executorService.submit(new PingCheck(result)));
        boolean done = false;
        while (true) {
            int count = futures.size();
            for (Future<Result> future : futures) {
                System.out.println("Future Job Interation");
                if (!result.isDown()) {
                    future.cancel(true);
                    System.out.println("reachable");
                    count = 0;
                    break;
                }
                if (future.isDone()) {
                    count--;
                    try {
                        result = future.get();
                        System.out.println("-> "+ result);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(!result.isDown() || count <=0){
                done = true;
                break;
            }
        }
        if(done){
            executorService.shutdownNow();
        }
    }
}
