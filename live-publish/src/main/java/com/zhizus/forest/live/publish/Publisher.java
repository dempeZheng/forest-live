package com.zhizus.forest.live.publish;

import akka.actor.ActorRef;
import com.zhizus.forest.live.common.codec.Response;


/**
 * Created by Dempe on 2017/1/12.
 */
public class Publisher {

    private final static LookupBusImpl lookupBus = new LookupBusImpl();

    public static boolean subscribe(Long topSid, Long subSid, ActorRef actorRef) {
        return lookupBus.subscribe(actorRef, buildTopic(topSid, subSid));
    }

    public static void publish(Long topSid, Long subSid, Response response) {
        lookupBus.publish(new MsgEnvelope(buildTopic(topSid, subSid), response));
    }

    public static void publish(Long topSid, Long subSid, Response response, ActorRef actorRef) {
        lookupBus.publish(new MsgEnvelope(buildTopic(topSid, subSid), response), actorRef);
    }

    public static String buildTopic(Long topSid, Long subSid) {
        return topSid + "_" + subSid;


    }

}
