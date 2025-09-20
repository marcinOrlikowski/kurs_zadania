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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public FFDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(FFDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Money getTotal() {
        return total;
    }

    public void setTotal(Money total) {
        this.total = total;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return getItemDescription();
    }
}
