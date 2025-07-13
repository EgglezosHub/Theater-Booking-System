import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class THServer extends THImpl {
    public static void main(String[] args) {
        try {
            // Create the implementation object
            THImpl impl = new THImpl();

            // Export it dynamically (no need to run rmic)
            THInterface stub = (THInterface) UnicastRemoteObject.exportObject(impl, 0);

            // Get or create registry
            Registry reg = LocateRegistry.getRegistry();
            reg.bind("THRegistry", stub);

            System.out.println("server ready");
        } catch (Exception e) {
            System.err.println("server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
