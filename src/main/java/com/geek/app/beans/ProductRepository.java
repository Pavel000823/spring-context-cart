package com.geek.app.beans;

import com.geek.app.objects.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(String id) {
        Optional<Product> productOptional = products.stream()
                .filter(p -> p.getId().equals(id)).findFirst();
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw new RuntimeException("fatal error: not found product with id = " + id);
    }

    public boolean isProduct(String id){
       return products.stream().anyMatch(p -> p.getId().equals(id));
    }

    @Autowired
    private void init() {
        for (int i = 1; i < 6; i++) {
            products.add(new Product(String.valueOf(i), "Product_" + i, (10 + i) * i));
        }
    }

    public void info() {
        System.out.println("Список доступных продуктов");
        for (Product product : products) {
            System.out.println("id: " + product.getId() + " Наименование: " + product.getName() + " Цена: " + product.getCost());
        }
    }
}
