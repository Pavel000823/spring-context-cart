package com.geek.app.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Actions {

    @Value("добавить продукт")
    private String add;
    @Value("удалить продукт")
    private String remove;
    @Value("посмотреть продукты в корзине")
    private String view;
    @Value("оплата")
    private String pay;
    @Value("повторить покупки")
    private String repeat;
    @Value("выйти")
    private String exit;

    private final HashMap<String, String> actions = new HashMap<>();

    @Autowired
    public void init() {
        actions.put("1", add);
        actions.put("2", remove);
        actions.put("3", view);
        actions.put("4", pay);
        actions.put("5", repeat);
        actions.put("6", exit);
    }

    public void printActions() {
        System.out.println("Введите одну из команд...");
        for (String action : actions.keySet()) {
            System.out.println(action + " : " + actions.get(action));
        }
    }

    public boolean isAction(String action) {
        return actions.containsKey(action);
    }

    public String getAction(String action) {
        return actions.get(action);
    }


    public String getAdd() {
        return add;
    }

    public String getRemove() {
        return remove;
    }

    public String getView() {
        return view;
    }

    public String getPay() {
        return pay;
    }

    public String getRepeat() {
        return repeat;
    }

    public String getExit() {
        return exit;
    }
}
