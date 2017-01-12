package com.zhizus.forest.live.service.processor;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import com.alibaba.fastjson.JSONObject;
import com.zhizus.forest.live.common.codec.Payload;
import com.zhizus.forest.live.common.codec.Request;
import com.zhizus.forest.live.common.codec.Response;
import com.zhizus.forest.live.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.Executor;

/**
 * Created by Dempe on 2017/1/12.
 */
public class ServiceProcessor {

    private static Executor executor = new StandardThreadExecutor();

    private ActorSelection actorSelection;

    private ServiceRouter router;

    public ServiceProcessor(ApplicationContext context, ActorSelection actorSelection) {
        this.actorSelection = actorSelection;
        router = new ServiceRouter(context);
    }

    public void execute(String uri, Request request) {
        ActionMethod actionMethod = this.router.router(uri);
        executor.execute(new InvokerRunnable(actionMethod, request, actorSelection));
    }

}

class InvokerRunnable implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(InvokerRunnable.class);

    private ActionMethod actionMethod;
    private Request request;
    private ActorSelection actorSelection;

    public InvokerRunnable(ActionMethod actionMethod, Request request, ActorSelection actorSelection) {
        this.actionMethod = actionMethod;
        this.request = request;
        this.actorSelection = actorSelection;
    }

    @Override
    public void run() {
        Object result = null;
        ForestContext.setForestContext(request, null);
        try {
            JSONObject params = (JSONObject) JSONObject.parse(request.payload().payload());
            String[] parameterNames = MethodParam.getParameterNames(actionMethod.getMethod());
            Object[] parameterValues = MethodParam.getParameterValues(parameterNames, actionMethod.getMethod(), params);
            request.payload().payload();
            result = actionMethod.rateLimiterInvoker(parameterValues);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            ForestContext.removeForestContext();
        }
        // rsp
        Response response = new Response(request.header(), new Payload(request.payload().uri(), JSONObject.toJSONBytes(result)));
        actorSelection.tell(response, ActorRef.noSender());
    }

}
