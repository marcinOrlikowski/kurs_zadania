package ffwork.domain.resource;

import ffwork.money.Money;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Room room = new Room("Classroom", 5, Set.of("Chairs", "Table", "whiteboard"));
        System.out.println(room.describe());
        Desk desk = new Desk("test desk", Money.of("10"), Desk.DeskType.HOT);
        System.out.println(desk.describe());
        Device device = new Device("device", 5);
        System.out.println(device.describe());
    }
}
