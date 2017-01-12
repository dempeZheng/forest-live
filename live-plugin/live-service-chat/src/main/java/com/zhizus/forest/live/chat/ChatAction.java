package com.zhizus.forest.live.chat;

import com.alibaba.fastjson.JSONObject;
import com.zhizus.forest.live.common.codec.Header;
import com.zhizus.forest.live.service.BaseAction;
import com.zhizus.forest.live.service.Param;
import com.zhizus.forest.live.service.ServiceMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 聊天服务
 * Created by Dempe on 2017/1/12.
 */
@ServiceMapping(service = "chat")
public class ChatAction extends BaseAction {

    private final static Logger LOGGER = LoggerFactory.getLogger(ChatAction.class);

    public JSONObject chat(@Param String msg) {
        Header header = header();
        LOGGER.info("chat msg:{}, header:{}", msg, header);
        JSONObject result = new JSONObject();
        result.put("code", 0);
        return result;
    }
}
