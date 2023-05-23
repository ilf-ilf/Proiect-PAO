import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class AuditService {
    private FileWriter auditWriter;

    public AuditService() {
        try {
            auditWriter = new FileWriter("audit.csv", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodă pentru înregistrarea unei acțiuni în fișierul de audit
    public void logAction(String action) {
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
