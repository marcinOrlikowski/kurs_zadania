package ffwork.discount;

import ffwork.domain.user.IndividualUser;
import ffwork.domain.user.User;
import ffwork.money.Money;


public class StudentDiscount implements Discountable {
    @Override
    public Money applyDiscount(Money base, User user) {
        if (user instanceof IndividualUser) {
            return base.multiply(0.8);
        }
        return base;
    }
}
