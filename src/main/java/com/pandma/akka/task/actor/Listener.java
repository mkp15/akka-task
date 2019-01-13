package com.pandma.akka.task.actor;

import akka.actor.UntypedActor;

public class Listener extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println("Listener received message "+message);
    }
}
