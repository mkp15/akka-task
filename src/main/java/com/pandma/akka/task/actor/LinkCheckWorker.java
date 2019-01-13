package com.pandma.akka.task.actor;

import akka.actor.UntypedActor;
import com.pandma.akka.task.Result;
import com.pandma.akka.task.linkcheck.LinkCheck;
import com.pandma.akka.task.util.Utils;

public class LinkCheckWorker extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof LinkCheck){
            LinkCheck linkCheck = (LinkCheck) message;
            Result result = linkCheck.check();
            System.out.println(this.sender());
            getSender().tell(result, getSelf());
           // this.sender().tell(result, getSelf());
        }
        else {
            System.out.println("LinkCheckWorker unhandled");
           unhandled(message);
        }

    }
}
