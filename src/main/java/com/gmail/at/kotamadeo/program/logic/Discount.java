package com.gmail.at.kotamadeo.program.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Discount {
    private Consumer consumer;
    private double itemPrice;
    private int itemCount;
    private int discountStartPrice, maxDiscountPrice;
    private final int MIN_DISCOUNT = 100;
    private final double MAX_DISCOUNT = 0.05, MUSIC_LOVER_DISCOUNT = 0.01;
    private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###");

    public Discount(double itemPrice, int itemCount, int discountStartPrice, int maxDiscountPrice) {
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.discountStartPrice = discountStartPrice;
        this.maxDiscountPrice = maxDiscountPrice;
    }

    public void consumerDiscount(Consumer consumer) {
        double endPrice;
        double totalPrice = itemPrice * itemCount;
        if (consumer.isMusicLover() == true) {
            if (totalPrice >= maxDiscountPrice) {
                endPrice = (totalPrice - (totalPrice * MAX_DISCOUNT) - (totalPrice * MUSIC_LOVER_DISCOUNT));
                BigDecimal roundEndPrice = new BigDecimal(Double.toString(endPrice));
                System.out.println("Вы приобрели товар в количестве " + itemCount + " шт. по цене " + DECIMAL_FORMAT.format(itemPrice) +
                        " шт/коп. (общая стоимость составляет: " + DECIMAL_FORMAT.format(totalPrice) + " коп.). Ваша скидка составляет " +
                        MAX_DISCOUNT * 100 + "%. Так же дополнительно, как постоянный клиент вы получаете скидку " + MUSIC_LOVER_DISCOUNT * 100 +
                        "%.\nВаша итоговая стоимость:" + DECIMAL_FORMAT.format(endPrice) + " коп.\nВаша округленная итоговая стоимость: " +
                        roundEndPrice.setScale(0, RoundingMode.HALF_DOWN).toString() + " руб.");
            } else if (totalPrice >= discountStartPrice) {
                endPrice = (totalPrice - MIN_DISCOUNT) - (totalPrice * MUSIC_LOVER_DISCOUNT);
                BigDecimal roundEndPrice = new BigDecimal(Double.toString(endPrice));
                System.out.println("Вы приобрели товар в количестве " + itemCount + " шт. по цене " + DECIMAL_FORMAT.format(itemPrice) +
                        " шт/коп. (общая стоимость составляет: " + DECIMAL_FORMAT.format(totalPrice) + " коп.). Ваша скидка составляет " +
                        MIN_DISCOUNT + " рублей. Так же дополнительно, как постоянный клиент вы получаете скидку " + MUSIC_LOVER_DISCOUNT * 100 +
                        "%.\nВаша итоговая стоимость:" + DECIMAL_FORMAT.format(endPrice) + " коп.\nВаша округленная итоговая стоимость: " +
                        roundEndPrice.setScale(0, RoundingMode.HALF_DOWN).toString() + " руб.");
            } else {
                endPrice = totalPrice - (totalPrice * MUSIC_LOVER_DISCOUNT);
                BigDecimal roundEndPrice = new BigDecimal(Double.toString(endPrice));
                System.out.println("Вы приобрели товар в количестве " + itemCount + " шт. по цене " + DECIMAL_FORMAT.format(itemPrice) +
                        " шт/коп. (общая стоимость составляет: " + DECIMAL_FORMAT.format(totalPrice) + " коп.). " +
                        "Ваша скидка как постоянного клиента составляет: " + MUSIC_LOVER_DISCOUNT * 100 +
                        "%.\nВаша итоговая стоимость:" + DECIMAL_FORMAT.format(endPrice) + " коп.\nВаша округленная итоговая стоимость: " +
                        roundEndPrice.setScale(0, RoundingMode.HALF_DOWN).toString() + " руб.");
            }
        } else {
            if (maxDiscountPrice < totalPrice) {
                endPrice = (totalPrice - (totalPrice * MAX_DISCOUNT));
                BigDecimal roundEndPrice = new BigDecimal(Double.toString(endPrice));
                System.out.println("Вы приобрели товар в количестве " + itemCount + " шт. по цене " + DECIMAL_FORMAT.format(itemPrice) +
                        " шт/коп. (общая стоимость составляет: " + DECIMAL_FORMAT.format(totalPrice) + " коп.). Ваша скидка составляет "
                        + MAX_DISCOUNT * 100 + "%.\nВаша итоговая стоимость:" + DECIMAL_FORMAT.format(endPrice) +
                        " коп.\nВаша округленная итоговая стоимость: " + roundEndPrice.setScale(0, RoundingMode.HALF_DOWN).toString() + " руб.");
            } else if (discountStartPrice < totalPrice) {
                endPrice = (totalPrice - MIN_DISCOUNT);
                BigDecimal roundEndPrice = new BigDecimal(Double.toString(endPrice));
                System.out.println("Вы приобрели товар в количестве " + itemCount + " шт. по цене " + DECIMAL_FORMAT.format(itemPrice) +
                        " шт/коп. (общая стоимость составляет: " + DECIMAL_FORMAT.format(totalPrice) + " коп.). Ваша скидка составляет " +
                        MIN_DISCOUNT + " рублей.\nВаша итоговая стоимость:" + DECIMAL_FORMAT.format(endPrice) +
                        " коп.\nВаша округленная итоговая стоимость: " + roundEndPrice.setScale(0, RoundingMode.HALF_DOWN).toString() + " руб.");
            } else {
                endPrice = totalPrice;
                BigDecimal roundEndPrice = new BigDecimal(Double.toString(endPrice));
                System.out.printf("Вы приобрели товар в количестве %s шт. по цене %s шт/коп.%nВаша итоговая стоимость: %s коп.%n" +
                                "Ваша округленная итоговая стоимость: %s руб.%n", itemCount, itemPrice, DECIMAL_FORMAT.format(endPrice),
                        roundEndPrice.setScale(0, RoundingMode.HALF_DOWN).toString());
            }
        }
    }
}
