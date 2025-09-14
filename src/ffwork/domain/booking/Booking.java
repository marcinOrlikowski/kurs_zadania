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

    public Booking(User user, Resource resource, FFDateTime start, FFDateTime end, BookingStatus status, Money calculatedPrice, Payment payment) {
        isFFDateTimeValid(start, end);
        this.id = idConstructor(start);
        this.user = user;
        this.Resource = resource;
        this.start = start;
        this.end = end;
        this.status = status;
        this.calculatedPrice = calculatedPrice;
        this.payment = payment;
        counter++;
    }

    private void isFFDateTimeValid(FFDateTime start, FFDateTime end) {
        if (start.toEpochMinutes() > end.toEpochMinutes()) {
            throw new IllegalArgumentException("End date must be later than start date");
        }
    }

    private String idConstructor(FFDateTime d) {
        String date = d.toString().split("T")[0].replaceAll("-", "");
        return String.format("BK-<%s>-<%d>", date, counter);
    }

    public void changeStatus(BookingStatus newStatus) {
        if (this.status == BookingStatus.PENDING && newStatus == BookingStatus.CONFIRMED || newStatus == BookingStatus.CANCELLED) {
            System.out.printf("Changed status from %s to %s%n", this.status, newStatus);
            this.status = newStatus;
        } else if (this.status == BookingStatus.CONFIRMED && newStatus == BookingStatus.COMPLETED || newStatus == BookingStatus.CANCELLED) {
            System.out.printf("Changed status from %s to %s%n", this.status, newStatus);
            this.status = newStatus;
        } else {
            throw new IllegalArgumentException(String.format("Cannot change from status %s to %s", this.status, newStatus));
        }
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


