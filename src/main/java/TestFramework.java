import akka.actor.*;
import com.pandma.akka.task.DeviceInfo;
import com.pandma.akka.task.actor.Listener;
import com.pandma.akka.task.actor.LinkCheckStarter;

import java.util.ArrayList;
import java.util.List;

public class TestFramework {
    public static void main(String[] args) {
        // Create an Akka system
        ActorSystem system = ActorSystem.create("LinkCheckSystem");

        // create the result listener, which will print the result and shutdown the system
        final ActorRef listener = system.actorOf(new Props(Listener.class), "listener");

        // create the master
        ActorRef starter = system.actorOf(new Props(new UntypedActorFactory() {
            public UntypedActor create() {
                return new LinkCheckStarter(listener);
            }
        }), "linkCheckStarter");

        for(int i =0;i< 1000;i++) {
            // start the calculation
            List<String> linkCheckTypes = new ArrayList<String>();
            linkCheckTypes.add("IPLink");
            linkCheckTypes.add("SNMPLink");
            linkCheckTypes.add("UrlLink");

            starter.tell(new DeviceInfo("10.4.23.34", "TEKO", linkCheckTypes));
        }
        // system.shutdown();
    }
}
