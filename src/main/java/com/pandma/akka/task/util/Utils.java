package com.pandma.akka.task.util;

import com.pandma.akka.task.Result;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    static int[] seeds = {200, 500, 10000};

    static Result[] results = {new Result("UP"), new Result("DOWN")};

    public static long getRandomSleep(){
        int randomIndex = ThreadLocalRandom.current().nextInt(0, 3);
        return seeds[randomIndex];
    }

    public static Result getLinkCheckResult(){
        int randomIndex = ThreadLocalRandom.current().nextInt(0, 2);
        return results[randomIndex];
    }
}
