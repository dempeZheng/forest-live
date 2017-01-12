package com.zhizus.forest.live.gift;

import com.alibaba.fastjson.JSONObject;
import com.zhizus.forest.live.common.codec.Header;
import com.zhizus.forest.live.service.BaseAction;
import com.zhizus.forest.live.service.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Dempe on 2017/1/12.
 */
public class GiftAction extends BaseAction {

    private final static Logger LOGGER = LoggerFactory.getLogger(GiftAction.class);

    public JSONObject send(@Param int type, @Param Long anchorUid, @Param Long giftId) {
        Header header = header();
        LOGGER.info("chat anchorUid:{},giftId:{}, header:{}", anchorUid, giftId, header);
        JSONObject result = new JSONObject();
        result.put("code", 0);
        return result;

    }
}
