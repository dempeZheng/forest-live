package com.zhizus.forest.demo;

import com.alibaba.fastjson.JSONObject;
import com.zhizus.forest.live.service.Param;
import com.zhizus.forest.live.service.Path;
import com.zhizus.forest.live.service.ServiceMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dempe on 2017/1/12.
 */
@ServiceMapping(service = "demo")
public class DemoAction {

    private final static Logger LOGGER = LoggerFactory.getLogger(DemoAction.class);

    @Path("hello")
    public JSONObject hello(@Param String name) {
        LOGGER.info("hello invoke, name:{}", name);
        JSONObject json = new JSONObject();
        json.put("code", 0);
        return json;
    }
}
