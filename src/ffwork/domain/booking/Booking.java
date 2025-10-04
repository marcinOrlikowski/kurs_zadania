package ffwork.domain.booking;

import ffwork.Payment.Payment;
import ffwork.domain.resource.Resource;
import ffwork.domain.user.User;
import ffwork.money.Money;
import ffwork.time.FFDateTime;

public class Booking {
    private String id;
    private User user;
    private Resource Resource;
    private FFDateTime start;
    private FFDateTime end;
    private BookingStatus status;
    private Money calculatedPrice;
    private Payment payment;

    private static int counter = 1;

    public Booking(User user, Resource resource, FFDateTime start, FFDateTime end) {
        validateDates(start, end);
        validateUser(user);
        validateResource(resource);
        this.id = idConstructor(start);
        this.user = user;
        this.Resource = resource;
        this.start = start;
        this.end = end;
        this.status = BookingStatus.PENDING;
        counter++;
    }

    private void validateDates(FFDateTime start, FFDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (start.toEpochMinutes() > end.toEpochMinutes()) {
            throw new IllegalArgumentException("End date must be later than start date");
        }
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
    }

    private void validateResource(Resource resource) {
        if (resource == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
    }

    private String idConstructor(FFDateTime d) {
        String date = d.toString().split("T")[0].replaceAll("-", "");
        return String.format("BK-<%s>-<%d>", date, counter);
    }


    public void changeStatus(BookingStatus newStatus) {
        BookingStatus oldStatus = this.status;
        if (isCancelable() && newStatus == BookingStatus.CANCELLED) {
            this.status = newStatus;
        } else if (isConfirmable() && newStatus == BookingStatus.CONFIRMED) {
            this.status = newStatus;
        } else if (isCompletable() && newStatus == BookingStatus.COMPLETED) {
            this.status = newStatus;
        } else {
            throw new IllegalArgumentException(String.format("Cannot change from status %s to %s", this.status, newStatus));
        }
        System.out.printf("Changed status from %s to %s%n", oldStatus, this.status);
    }

    private boolean isCancelable() {
        return this.status == BookingStatus.PENDING || this.status == BookingStatus.CONFIRMED;
    }

    private boolean isConfirmable() {
        return this.status == BookingStatus.PENDING;
    }

    private boolean isCompletable() {
        return this.status == BookingStatus.CONFIRMED;
    }

    public int durationMinutes() {
        return end.toEpochMinutes() - start.toEpochMinutes();
    }

    public String getId() {
        return id;
    }

    public Resource getResource() {
        return Resource;
    }

    public User getUser() {
        return user;
    }

    public FFDateTime getStart() {
        return start;
    }

    public FFDateTime getEnd() {
        return end;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Money getCalculatedPrice() {
        return calculatedPrice;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setCalculatedPrice(Money calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", Resource=" + Resource +
                ", start=" + start +
                ", end=" + end +
                ", status=" + status +
                ", calculatedPrice=" + calculatedPrice +
                ", payment=" + payment +
                '}';
    }
}


