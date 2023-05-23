Acest exemplu de aplicație pentru sistemul de e-ticketing. 
Utilizează clasele Ticket, TicketService, CSVService și AuditService pentru reprezentarea, gestionarea biletelor și 
serviciile asociate. 
Colecția tickets este folosită pentru stocarea biletelor, iar colecțiile sunt implementate utilizând List<Ticket>.

Fișierele CSV sunt utilizate pentru persistența datelor. 
CSVService oferă metode pentru citirea și scrierea biletelor în/din fișiere CSV. 
De asemenea, AuditService este responsabil pentru înregistrarea acțiunilor într-un fișier de audit.

Clasa Main reprezintă punctul de intrare în aplicație și oferă un meniu interactiv în consolă pentru efectuarea 
acțiunilor definite. 
La pornirea aplicației, datele sunt încărcate din fișierul tickets.csv, iar la închiderea aplicației, 
datele sunt salvate în același fișier. 
Acțiunile utilizatorului și marcajele de timp asociate sunt înregistrate în fișierul de audit audit.csv.

