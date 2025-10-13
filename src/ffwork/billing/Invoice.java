package ffwork.billing;

import ffwork.domain.user.User;
import ffwork.money.Money;
import ffwork.time.FFDateTime;

public class Invoice {
    private String invoiceNumber;
    private FFDateTime issueDate;
    private User buyer;
    private Money total;
    private String itemDescription;

    public Invoice(String invoiceNumber, FFDateTime issueDate, User buyer, Money total, String itemDescription) {
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.buyer = buyer;
        this.total = total;
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return String.format("Invoice total=%s buyer=%s", total, buyer.getDisplayName());
    }
}
