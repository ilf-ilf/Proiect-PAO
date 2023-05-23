import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVService {
    private static CSVService instance;

    private CSVService() {}

    public static CSVService getInstance() {
        if (instance == null) {
            instance = new CSVService();
        }
        return instance;
    }

    // Metodă pentru citirea biletelor din fișierul CSV
    public List<Ticket> readTicketsFromFile(String filename) {
        List<Ticket> tickets = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String eventId = data[0];
                String eventName = data[1];
                double price = Double.parseDouble(data[2]);
                int quantity = Integer.parseInt(data[3]);
                Ticket ticket = new Ticket(eventId, eventName, price, quantity);
                tickets.add(ticket);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // Metodă pentru scrierea biletelor în fișierul CSV
    public void writeTicketsToFile(List<Ticket> tickets, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Ticket ticket : tickets) {
                String line = ticket.getEventId() + "," + ticket.getEventName() + "," + ticket.getPrice() + "," + ticket.getQuantity();
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
