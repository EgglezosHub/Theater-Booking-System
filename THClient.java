import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class THClient {    
    // Usage guide
    public static void usage() {
        System.err.println("usage: java THClient list <hostname>");
        System.err.println("       java THClient book <type> <number> <name> <hostname>");
        System.err.println("       java THClient guests <hostname>");
        System.err.println("       java THClient cancel <type> <number> <name> <hostname>");
        System.exit(1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "";

        try {
            // Validate argument count
            if (args.length != 2 && args.length != 5)
                usage();
            String host = args[args.length - 1];
			THInterface stub = (THInterface) Naming.lookup("rmi://" + host + "/THRegistry");

            // Call corresponding method based on input
            if (args[0].equals("list") && args.length == 2)
                str = stub.list();
            else if (args[0].equals("book") && args.length == 5) {
                str = stub.book(args[1], Integer.parseInt(args[2]), args[3]);
                if (str.equals("fail: no seats available")) {
                    System.out.println("Η ζώνη δεν έχει διαθεσιμότητα.");
                    System.out.print("Θέλετε να ειδοποιηθείτε όταν υπάρξει διαθεσιμότητα; (y/n): ");
                    if (sc.next().equals("y")) {
                        stub.addListSub(args[3], args[1]);
                        System.out.println("Ευχαριστούμε, θα ειδοποιηθείτε.");
                        return;
                    }
                }
            } else if (args[0].equals("guests") && args.length == 2)
                str = stub.guests();
            else if (args[0].equals("cancel") && args.length == 5)
                str = stub.cancel(args[1], Integer.parseInt(args[2]), args[3]);
            else
                usage();
        } catch (RemoteException e) {
            System.err.println("RemoteException: " + e.toString());
        } catch (NotBoundException e) {
            System.err.println("NotBoundException: " + e.toString());
        } catch (Exception e) {
            System.err.println("Exception: " + e.toString());
        }

        System.out.println(str);
    }
}
