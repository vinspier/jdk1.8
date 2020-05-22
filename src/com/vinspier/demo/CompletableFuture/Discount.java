package com.vinspier.demo.CompletableFuture;

import static com.vinspier.demo.CompletableFuture.Shop.delay;

/**
 * @ClassName: Discount
 * @Description:
 * @Author:
 * @Date: 2020/5/22 11:33
 * @Version V1.0
 **/
public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;
        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(),
                        quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
        delay();
        return (price * (100 - code.percentage) / 100);
    }
}
