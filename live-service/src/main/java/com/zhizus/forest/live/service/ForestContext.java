package com.zhizus.forest.live.service;

import com.google.common.collect.Maps;
import com.zhizus.forest.live.common.codec.Request;
import com.zhizus.forest.live.common.codec.Response;

import java.util.Map;

/**
 * Created by Dempe on 2016/12/7.
 */
public class ForestContext {

    private final static ThreadLocal<ForestContext> contextMap = new ThreadLocal<ForestContext>();

    private Request request;
    private Response response;

    private Map<String, String> attrs = Maps.newHashMap();

    private ForestContext(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    public static Request request() {
        return getForestContext().request;
    }

    public static Response response() {
        return getForestContext().response;
    }

    public static String getAttr(String key) {
        return getForestContext().attrs.get(key);
    }

    public static String putAttr(String key, String value) {
        return getForestContext().attrs.put(key, value);
    }

    public static ForestContext getForestContext() {
        ForestContext forestContext = contextMap.get();
        return forestContext;
    }

    public static void removeForestContext() {
        contextMap.remove();

    }

    public static void setForestContext(Request request, Response response) {
        contextMap.set(new ForestContext(request, response));
    }

}
