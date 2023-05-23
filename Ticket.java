public class Ticket {
    private String eventId;
    private String eventName;
    private double price;
    private int quantity;

    public Ticket(String eventId, String eventName, double price, int quantity) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.price = price;
        this.quantity = quantity;
    }

    // Metode de acces pentru atribute
    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
