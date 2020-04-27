import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {

    public static void main(String[] args) throws IOException {
        // IP address
        String serverAdress = "192.168.1.12";
        // the port where the server is offering the service
        int PORT = 8181;

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String request, response;
        Scanner sc = new Scanner(System.in);

        try{
            //We get the socket in order to communicate with the server
            socket = new Socket(serverAdress, PORT);

            // We create the out object in order to write data to the server
            out = new PrintWriter(socket.getOutputStream(), true);

            // We create the in object in order to read data from the server
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //We take the request from keyboard
            request = sc.nextLine();

            // While "exit" is not given
            while(!request.equalsIgnoreCase("exit")){
                // Send the request to the server
                out.println(request);

                // We take the response from the server
                response = in.readLine();
                System.out.println(response);

                request = sc.nextLine();


            }


        } catch (UnknownHostException e) {
            System.err.println("Serverul nu poate fi gasit " + e);
            System.exit(2);
        }finally {
            // We close the printWriter object if it's opened
            if(out != null)
                out.close();
            // We close the bufferedReader object if it's opened
            if( in != null)
                in.close();
            // We close the connection if it's opened
            if( socket != null);
                socket.close();

        }

    }

}
