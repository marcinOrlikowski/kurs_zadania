package enum_;

public enum OrderStatus {
    NEW, PROCESSING, SHIPPED, DELIVERED, CANCELED;

    public boolean isActive(){
        return this == NEW || this == PROCESSING || this == SHIPPED;
    }
}
