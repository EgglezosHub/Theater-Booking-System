import java.rmi.Remote;
import java.rmi.RemoteException;

// Defines remote methods callable by the client
public interface THInterface extends Remote {
    String list() throws RemoteException;
    String book(String type, int num, String name) throws RemoteException;
    String guests() throws RemoteException;
    String cancel(String type, int num, String name) throws RemoteException;
    void addListSub(String name, String type) throws RemoteException;
}
