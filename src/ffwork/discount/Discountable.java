package ffwork.discount;

import ffwork.domain.user.User;
import ffwork.money.Money;

public interface Discountable {
    Money applyDiscount(Money base, User user);
}
