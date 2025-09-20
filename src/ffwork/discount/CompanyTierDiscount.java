package ffwork.discount;

import ffwork.domain.user.CompanyUser;
import ffwork.domain.user.User;
import ffwork.money.Money;

import java.util.HashMap;
import java.util.Map;

public class CompanyTierDiscount implements Discountable {
    @Override
    public Money applyDiscount(Money base, User user) {
        Map<String, Double> discountByTaxId = new HashMap<>();
        discountByTaxId.put("taxIdNumber1", 0.3);
        discountByTaxId.put("taxIdNumber2", 0.4);

        if (user instanceof CompanyUser) {
            Double discount = discountByTaxId.get(((CompanyUser) user).getTaxId());
            if (discount != null) {
                return base.multiply(1 - discount);
            }
        }
        return base;
    }
}
