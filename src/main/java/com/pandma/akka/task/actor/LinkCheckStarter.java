package com.pandma.akka.task.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import com.pandma.akka.task.DeviceInfo;
import com.pandma.akka.task.Result;
import com.pandma.akka.task.linkcheck.IPLinkCheck;
import com.pandma.akka.task.linkcheck.SnmpLinkCheck;
import com.pandma.akka.task.linkcheck.UrlLinkCheck;


public class LinkCheckStarter extends UntypedActor {
    private final ActorRef listener;
    private final ActorRef workerRouter;
    private boolean terminate;
    private int totalLinkCheckStrategies;
    private int receivedResults = 0;

    public LinkCheckStarter(ActorRef listener) {
        this.listener = listener;
        this.workerRouter = getContext().actorOf(new Props(LinkCheckWorker.class)
                .withRouter(new RoundRobinRouter(30)), "workerRouter");
    }


    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println("In Supervisor Actor");
        if (message instanceof DeviceInfo) {
            DeviceInfo deviceInfo = (DeviceInfo) message;
            this.totalLinkCheckStrategies = deviceInfo.getSupportedLinkChecks().size();
            // use visitor pattern here
            for (String linkCheckType : deviceInfo.getSupportedLinkChecks()) {
                if("IPLink".equals(linkCheckType)) {
                    workerRouter.tell(new IPLinkCheck(), getSelf());
                }else if("SNMPLink".equals(linkCheckType)){
                    workerRouter.tell(new SnmpLinkCheck(), getSelf());
                }else{
                    workerRouter.tell(new UrlLinkCheck(), getSelf());
                }
            }
        } else if (message instanceof Result){
            Result result = (Result) message;
            String status = result.getValue();
            receivedResults = receivedResults + 1;
            System.out.println(getSender() + " Supervisor received response "+result);
           // System.out.println();
           // if (status.equals("UP")) {
           //     terminate = true;
          //  }

           // if (terminate || (receivedResults == totalLinkCheckStrategies)) {
                listener.tell(result, getSelf());
               // getContext().stop(getSelf());
               // getContext().stop(getSender());
           // }
        }
        else {
            System.out.println(getSender() + " Supervisor unhandled ");
            unhandled(message);
        }

    }
}
