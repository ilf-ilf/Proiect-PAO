import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketService ticketService = new TicketService();
        CSVService csvService = CSVService.getInstance();
        AuditService auditService = new AuditService();

        // Încărcarea datelor din fișier la pornirea aplicației
        List<Ticket> tickets = csvService.readTicketsFromFile("tickets.csv");
        ticketService.addTicket(tickets);

        boolean running = true;
        while (running) {
            System.out.println("\n===== Meniu =====");
            System.out.println("1. Adăugare bilet");
            System.out.println("2. Ștergere bilet");
            System.out.println("3. Căutare bilet după ID eveniment");
            System.out.println("4. Listare toate biletele");
            System.out.println("5. Calcul valoare totală bilete");
            System.out.println("6. Ieșire");

            System.out.print("Alegeți o opțiune: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            switch (option) {
                case 1:
                    System.out.print("Introduceți ID eveniment: ");
                    String eventId = scanner.nextLine();
                    System.out.print("Introduceți nume eveniment: ");
                    String eventName = scanner.nextLine();
                    System.out.print("Introduceți preț: ");
                    double price = scanner.nextDouble();
                    System.out.print("Introduceți cantitate: ");
                    int quantity = scanner.nextInt();
                    ticketService.addTicket(new Ticket(eventId, eventName, price, quantity));
                    auditService.logAction("Adăugare bilet");
                    break;
                case 2:
                    System.out.print("Introduceți ID eveniment pentru ștergere: ");
                    String deleteEventId = scanner.nextLine();
                    ticketService.deleteTicket(deleteEventId);
                    auditService.logAction("Ștergere bilet");
                    break;
                case 3:
                    System.out.print("Introduceți ID eveniment pentru căutare: ");
                    String searchEventId = scanner.nextLine();
                    Ticket ticket = ticketService.getTicketById(searchEventId);
                    if (ticket != null) {
                        System.out.println("Bilet găsit:");
                        System.out.println("ID eveniment: " + ticket.getEventId());
                        System.out.println("Nume eveniment: " + ticket.getEventName());
                        System.out.println("Preț: " + ticket.getPrice());
                        System.out.println("Cantitate: " + ticket.getQuantity());
                    } else {
                        System.out.println("Niciun bilet găsit pentru ID evenimentul specificat.");
                    }
                    break;
                case 4:
                    List<Ticket> allTickets = ticketService.getAllTickets();
                    if (allTickets.isEmpty()) {
                        System.out.println("Nu există bilete disponibile.");
                    } else {
                        System.out.println("Bilete disponibile:");
                        for (Ticket t : allTickets) {
                            System.out.println("ID eveniment: " + t.getEventId());
                            System.out.println("Nume eveniment: " + t.getEventName());
                            System.out.println("Preț: " + t.getPrice());
                            System.out.println("Cantitate: " + t.getQuantity());
                            System.out.println("-------------------------");
                        }
                    }
                    break;
                case 5:
                    double totalValue = ticketService.calculateTotalValue();
                    System.out.println("Valoarea totală a biletelor disponibile: " + totalValue);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Opțiune invalidă. Vă rugăm să alegeți din nou.");
                    break;
            }
        }

        // Salvarea datelor în fișier la închiderea aplicației
        csvService.writeTicketsToFile(ticketService.getAllTickets(), "tickets.csv");

        // Închiderea fișierului de audit
        auditService.closeAuditFile();
    }
}
