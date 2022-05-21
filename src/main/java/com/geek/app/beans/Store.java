package com.geek.app.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class Store {

    private final ProductRepository productRepository;
    private final CartFactory cartFactory;
    private final Actions actions;
    private final List<Cart> carts = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);


    public Store(ProductRepository productRepository, CartFactory cartFactory, Actions actions) {
        this.productRepository = productRepository;
        this.cartFactory = cartFactory;
        this.actions = actions;
    }

    @Autowired
    public void bay() {
        productRepository.info();
        actions.printActions();
        while (true) {
            Cart cart = cartFactory.getCart();
            while (true) {
                String action = scanner.nextLine();
                if (!actions.isAction(action)) {
                    System.out.println("Введена некорректная команда...");
                    continue;
                }
                if (actions.getAction(action).equals(actions.getAdd())) {
                    System.out.println("Введите id продукта");
                    while (true) {
                        String id = scanner.nextLine();
                        if (actions.isAction(id)) {
                            cart.add(productRepository.getProduct(id));
                            System.out.println("Продукт добавлен");
                            break;
                        }
                        System.out.println("Нет продукта с id " + id);
                    }
                }
                if (actions.getAction(action).equals(actions.getRemove())) {
                    System.out.println("Введите id продукта для удаления");
                    if (cart.size() == 0) {
                        System.out.println("Корзина пуста");
                        continue;
                    }
                    while (true) {
                        String id = scanner.nextLine();
                        if (productRepository.isProduct(id)) {
                            cart.remove(productRepository.getProduct(id));
                            System.out.println("Продукт успешно удален");
                            break;
                        }
                        System.out.println("В корзине нет продукта с id " + id);
                    }
                }
                if (actions.getAction(action).equals(actions.getView())) {
                    if (cart.size() == 0) {
                        System.out.println("Корзина пустая");
                        continue;
                    }
                    cart.info();
                }
                if (actions.getAction(action).equals(actions.getPay())) {
                    System.out.println("Покупки совершены");
                    carts.add(cart);
                    break;
                }
                if (actions.getAction(action).equals(actions.getExit())) {
                    return;
                }
                if (actions.getAction(action).equals(actions.getRepeat())) {
                    System.out.println("Вы не совершили покупку");
                }
            }
            System.out.println("Если хотите повторить покупки введите команду - 5. " +
                    "Если хотите покинуть store, введите команду 6");

            while (true) {
                String action = scanner.nextLine();
                if (!actions.isAction(action)) {
                    System.out.println("Введена некорректная команда...");
                    continue;
                }
                if (actions.getAction(action).equals(actions.getRepeat())) {
                    continue;
                }
                if (actions.getAction(action).equals(actions.getExit())) {
                    return;
                }
            }
        }
    }

    @PreDestroy
    private void storeInfo() {
        System.out.println("Информация о корзинах:");
        System.out.println("Количество корзин: " + carts.size());
        System.out.println("Корзины: " + carts.toString());
    }
}

