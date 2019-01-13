package com.pandma.akka.task.linkcheck;

import com.pandma.akka.task.Result;
import com.pandma.akka.task.util.Utils;

public class UrlLinkCheck implements LinkCheck {

    @Override
    public Result check() throws Exception {
        System.out.println("Url Link check started");
        Thread.sleep(Utils.getRandomSleep());
        Result result = Utils.getLinkCheckResult();
        System.out.println("Url Link check result available now "+result);
        //this.sender().tell(Utils.getLinkCheckResult());
        return Utils.getLinkCheckResult();
    }
}
