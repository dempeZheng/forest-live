package com.zhizus.forest.live.service;

import com.zhizus.forest.live.common.codec.Header;
import com.zhizus.forest.live.common.codec.Request;

/**
 * Created by Dempe on 2017/1/12.
 */
public class BaseAction {

    public Header header() {
        return ForestContext.request().header();
    }

    public Request request() {
        return ForestContext.request();
    }
}
