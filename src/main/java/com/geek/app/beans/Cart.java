package com.geek.app.beans;

import com.geek.app.objects.Product;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Cart {

    private final List<Product> cart = new ArrayList<>();


    public void add(Product product) {
        cart.add(product);
    }

    public void remove(Product product) {
        cart.remove(product);
    }

    public void info() {
        System.out.println("В корзине " + cart.toString());
    }

    public int size() {
        return cart.size();
    }
}
