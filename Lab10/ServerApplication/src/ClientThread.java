import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
    Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }


    public void run(){
        String response, request;
        try{
            //"in" is the request from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //"out" is the response from the server to the client
            PrintWriter out = new PrintWriter((socket.getOutputStream()));

            //we get the request
            request = in.readLine();

            // we check to see if the client wants to stop the server
            if(request == null){
                response = "Server stopped";
            } else {
                response = "Server received the request " + request;
            }
            // we send the response
            out.println(response);
            out.flush();


        } catch (IOException e) {
            System.err.println ("IO Error " + e ) ;
        }finally {
            // We close the socket
            try {
                socket.close();
            } catch ( IOException e ) {
                System.err.println("The socket cannot be closed: " + e);
            }

        }

    }
}
