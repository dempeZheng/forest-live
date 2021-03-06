package com.zhizus.forest.live.service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.zhizus.forest.live.service.interceptor.InvokerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Dempe on 2017/1/12.
 */
public class ServiceRouter implements IRouter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceRouter.class);

    private Map<String, ActionMethod> routerMapping = Maps.newConcurrentMap();

    private ApplicationContext context;

    public ServiceRouter(ApplicationContext context) {
        this.context = context;
        init();
    }


    public void init() {
        for (String beanName : context.getBeanNamesForAnnotation(ServiceMapping.class)) {
            Object bean = context.getBean(beanName);
            ServiceMapping serviceMapping = bean.getClass().getAnnotation(ServiceMapping.class);
            String service = Strings.isNullOrEmpty(serviceMapping.service()) ? bean.getClass().getSimpleName() : serviceMapping.service();
            // init router
            init(service, bean);
        }
    }

    public void init(String service, Object bean) {
        for (Method method : bean.getClass().getMethods()) {
            Path pathAnnotation = method.getAnnotation(Path.class);
            if (pathAnnotation == null) {
                continue;
            }
            String path = Strings.isNullOrEmpty(pathAnnotation.value()) ? method.getName() : pathAnnotation.value();
            ActionMethod actionMethod = new ActionMethod(bean, method);

            String uri = service + "/" + path;
            routerMapping.put(uri, actionMethod);
            initInterceptor(actionMethod, context);
            initRate(actionMethod);
        }
    }

    public void initRate(ActionMethod actionMethod) {
        Rate rate = actionMethod.getMethod().getAnnotation(Rate.class);
        if (rate != null) {
            int value = rate.value();
            if (value > 0) {
                actionMethod.setRateLimiter(RateLimiter.create(value));
            } else {
                LOGGER.warn("Rate value < 0 !");
            }
        }
    }

    public void initInterceptor(ActionMethod actionMethod, ApplicationContext context) {

        Interceptor interceptor = actionMethod.getMethod().getAnnotation(Interceptor.class);
        if (interceptor != null) {
            String id = interceptor.value();
            if (Strings.isNullOrEmpty(id) && context != null) {
                Class<?> clazz = interceptor.clazz();
                if (clazz == Object.class) {
                    LOGGER.warn("Interceptor id is empty !");
                } else {
                    Object bean = context.getBean(clazz);
                    if (bean != null && bean instanceof InvokerInterceptor) {
                        actionMethod.addInterceptorList((InvokerInterceptor) bean);
                    }
                }

            } else {
                for (String beanId : id.split(",")) {
                    Object bean = context.getBean(beanId);
                    if (bean != null && bean instanceof InvokerInterceptor) {
                        InvokerInterceptor invokerInterceptor = (InvokerInterceptor) bean;
                        actionMethod.addInterceptorList(invokerInterceptor);
                    }
                }
            }
        }
    }

    @Override
    public ActionMethod router(String uri) {
        return routerMapping.get(uri);
    }
}
