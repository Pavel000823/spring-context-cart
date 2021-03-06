package com.geek.app;

import com.geek.app.beans.Store;
import com.geek.app.config.ConfigApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigApplication.class);
        context.getBean(Store.class).bay();
    }
}
