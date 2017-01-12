package com.forest.zhizus.live.service;

import org.springframework.stereotype.Component;

/**
 * Created by Dempe on 2017/1/12.
 */
@Component
public @interface URIMapping {
    int uri() default 0;
}
