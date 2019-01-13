package com.pandma.akka.task.linkcheck;

import com.pandma.akka.task.Result;
import com.pandma.akka.task.util.Utils;

public class SnmpLinkCheck implements LinkCheck {
    //private ActorRef sender;
    @Override
    public Result check() throws Exception {
        System.out.println("SNMP Link check started");
        Thread.sleep(Utils.getRandomSleep());
        Result result = Utils.getLinkCheckResult();
        System.out.println("SNMP LINK check result available now "+result);
       // sender.tell();
        return result;
    }

}
