import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketService {
    private List<Ticket> tickets;
    private FileWriter auditWriter;

    public TicketService() {
        tickets = new ArrayList<>();
        try {
            auditWriter = new FileWriter("audit.csv", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodă pentru adăugarea unui bilet nou
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        writeAudit("Adăugare bilet");
    }

    // Metodă pentru ștergerea unui bilet
    public void deleteTicket(String eventId) {
        tickets.removeIf(ticket -> ticket.getEventId().equals(eventId));
        writeAudit("Ștergere bilet");
    }

    // Metodă pentru obținerea unui bilet după ID-ul evenimentului
    public Ticket getTicketById(String eventId) {
        for (Ticket ticket : tickets) {
            if (ticket.getEventId().equals(eventId)) {
                return ticket;
            }
        }
        return null;
    }

    // Metodă pentru obținerea tuturor biletelor
    public List<Ticket> getAllTickets() {
        return tickets;
    }

    // Metodă pentru calcularea valorii totale a biletelor disponibile
    public double calculateTotalValue() {
        double totalValue = 0.0;
        for (Ticket ticket : tickets) {
            totalValue += ticket.getPrice() * ticket.getQuantity();
        }
        return totalValue;
    }

    // Metodă pentru scrierea în fișierul de audit
    private void writeAudit(String action) {
        try {
            auditWriter.write(action + "," + new Date().getTime() + "\n");
            auditWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodă pentru închiderea fișierului de audit
    public void closeAuditFile() {
        try {
            auditWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
