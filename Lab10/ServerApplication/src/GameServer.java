import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    public static final int PORT = 8181;

    public static void main(String[] args){
        try {
            GameServer server = new GameServer();
        } catch (IOException e){
            System.err.println("IO Error " + e);
        }
    }

    public GameServer()throws IOException {
        //We create the socket object in order to communicate with the client
        ServerSocket serverSocket = null;

        try{
            //We set the server socket with the specific port
            serverSocket = new ServerSocket(PORT);
            while(true) {
                //We wait for a client
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();

                // We handle the request in a thread
                ClientThread t = new ClientThread(socket);
                t.start();
            }
        }catch (IOException e){
            System.err.println("IO Error " + e);
        }finally {
            serverSocket.close();
        }

    }
}
