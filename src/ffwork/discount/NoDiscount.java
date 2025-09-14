package ffwork.discount;

import ffwork.domain.user.User;
import ffwork.money.Money;

public class NoDiscount implements Discountable{
    @Override
    public Money applyDiscount(Money base, User user) {
        return base;
    }
}
