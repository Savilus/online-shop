package pl.sda.pol122.auctionservice.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
    }

    public static <T>T getBean(Class<T> cls){
        return context.getBean(cls);
    }

}
