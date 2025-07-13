// Implements the actual logic for theater booking operations
public class THImpl implements THInterface {

    // Array of seat types initialized with dummy data
    SeatType[] seats = {
        new SeatType("ΠΑ", "Πλατεία - Ζώνη Α", 50, 100),
        new SeatType("ΠΒ", "Πλατεία - Ζώνη Β", 40, 200),
        new SeatType("ΠΓ", "Πλατεία - Ζώνη Γ", 30, 300),
        new SeatType("ΚΕ", "Κεντρικός Εξώστης", 35, 250),
        new SeatType("ΠΘ", "Πλαϊνά Θεωρεία", 25, 50)
    };

    // Lists all seat types and their availability
    public String list() {
        StringBuilder sb = new StringBuilder();
        for (SeatType s : seats)
            sb.append(s.toString()).append("\n");
        return sb.toString();
    }

    // Books seats for a user
    public String book(String type, int num, String name) {
        for (SeatType s : seats) {
            if (type.equals(s.type))
                return s.addGuest(name, num);
        }
        return fail();
    }

    // Lists all guests and their bookings
    public String guests() {
        StringBuilder sb = new StringBuilder();
        int n = 0;
        for (SeatType s : seats)
            n += s.totalGuests();
        sb.append("total guests: ").append(n).append("\n");
        for (SeatType s : seats) {
            sb.append(s.toString()).append("\n");
            if ((n = s.totalGuests()) != 0)
                sb.append(s.listGuests());
        }
        return sb.toString();
    }

    // Cancels seat reservations
    public String cancel(String type, int num, String name) {
        for (SeatType s : seats) {
            if (type.equals(s.type))
                return s.removeReserv(name, num);
        }
        return fail();
    }
    
    // Adds a guest to notification list for a seat type
    public void addListSub(String name, String type) {
        for (SeatType s : seats) {
            if (type.equals(s.type)) {
                s.addSub(name);
                break;
            }
        }
    }

    // Helper method: fallback message if seat type doesn't exist
    private String fail() {
        return "fail: seat type doesn't exist. available seat types:\n" + list();
    }
}
