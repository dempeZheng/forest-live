package com.zhizus.forest.live.base;

import com.alibaba.fastjson.JSONObject;
import com.zhizus.forest.live.common.codec.Header;
import com.zhizus.forest.live.service.BaseAction;
import com.zhizus.forest.live.service.Path;
import com.zhizus.forest.live.service.ServiceMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dempe on 2017/1/12.
 */
@ServiceMapping(service = "channel")
public class ChannelAction extends BaseAction {
    private final static Logger LOGGER = LoggerFactory.getLogger(ChannelAction.class);

    /**
     * 用户进频道
     *
     * @return
     */
    @Path("inChannel")
    public JSONObject inChannel() {
        Header header = header();
        LOGGER.info("inChannel header:{}", header);
        JSONObject result = new JSONObject();
        return result;
    }

    /**
     * 用户退频道
     *
     * @return
     */
    @Path("outChannel")
    public JSONObject outChannel() {
        JSONObject result = new JSONObject();
        LOGGER.info("inChannel header:{}", header());
        return result;
    }
}
