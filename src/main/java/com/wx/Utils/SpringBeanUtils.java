package com.wx.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanUtils {

    @Autowired
    private ApplicationContext applicationContext;

    public <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    public <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}
