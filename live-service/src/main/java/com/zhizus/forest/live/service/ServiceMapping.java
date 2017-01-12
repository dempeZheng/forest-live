package com.zhizus.forest.live.service;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by Dempe on 2017/1/12.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ServiceMapping {
    String service() default "";
}
