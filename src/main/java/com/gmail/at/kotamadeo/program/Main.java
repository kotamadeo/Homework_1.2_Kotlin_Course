package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.program.logic.Consumer;
import com.gmail.at.kotamadeo.program.logic.Discount;

public class Main {
    public static void main(String[] args) {
        Consumer ivan = new Consumer(true);
        Consumer anna = new Consumer(false);
        Discount discount = new Discount(300.99, 11, 2999, 8999);
        Discount discount1 = new Discount(999.99, 11, 2999, 8999);
        Discount discount2 = new Discount(100.99, 5, 2999, 8999);
        System.out.println("Демонстрация работы логики:\n");
        discount.consumerDiscount(ivan);
        System.out.println();
        discount.consumerDiscount(anna);
        System.out.println("\n");
        discount1.consumerDiscount(ivan);
        System.out.println();
        discount1.consumerDiscount(anna);
        System.out.println("\n");
        discount2.consumerDiscount(ivan);
        System.out.println();
        discount2.consumerDiscount(anna);
    }
}
