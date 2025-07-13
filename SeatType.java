import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Represents a type/category of seats (e.g., Zone A, Zone B, etc.)
public class SeatType {
    public String type;       // Unique code for the seat type (e.g., "ΠΑ")
    public String desc;       // Description of the seat type (e.g., "Πλατεία - Ζώνη Α")
    public int price;         // Price per seat
    public int avail;         // Available number of seats
    private HashMap<String, Integer> guests = new HashMap<>();      // Map of guest name to reserved seats
    private ArrayList<String> listsubs = new ArrayList<>();         // List of guests who want notification

    // Constructor
    public SeatType(String type, String desc, int price, int avail) {
        this.type = type;       
        this.desc = desc;
        this.price = price;
        this.avail = avail;     // Available number of seats
    }

    // Attempt to add a reservation for a guest
    public String addGuest(String name, int num) {
        if (num <= 0)
            return "fail: cannot book <= 0 seats";
        else if (avail - num >= 0) {
            guests.put(name, guests.getOrDefault(name, 0) + num);
            avail -= num;
            return "success: " + (num * price) + " euros";
        } else if (avail > 0)
            return "fail: can only book " + avail + " seats";
        else
            return "fail: no seats available";
    }

    // Cancel reservation
    public String removeReserv(String name, int num) {
        String str = "";
        int n;
        if (!guests.containsKey(name))
            return "guest '" + name + "' doesn't exist";

        if ((n = guests.get(name)) >= num) {
            guests.put(name, n - num);
            avail += num;
            n -= num;
            if (n == 0) {
                guests.remove(name);
                str += "success";
            } else
                str += "success: " + n + " reservations left";
                for (String sub : listsubs) {
                    str += "\n[Notification for " + sub + "]: " + avail + " new seats available!";
                }
                listsubs.clear(); // clear after notifying

                return str;
        } else
            return "fail: cannot remove more (" + num + ") than booked (" + n + ")";
    }

    // List guests for this seat type
    public String listGuests() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> g : guests.entrySet())
            sb.append("\t").append(g.getKey()).append(" (").append(g.getValue()).append(")\n");
        return sb.toString();
    }

    // Return number of distinct guests
    public int totalGuests() {
        return guests.size();
    }

    // Add a guest to the subscription list
    public void addSub(String name) {
        listsubs.add(name);
    }

    // Notify subscribed users when availability changes
    public String notifySubs(String name) {
        if (listsubs.contains(name))
            return "\nnew " + avail + " seats available!";
        else
            return "";
    }

    // Custom string representation
    public String toString() {
        return avail + " θέσεις " + desc + " (κωδικός: " + type + ") - τιμή: " + price + " Ευρώ";
    }
}
