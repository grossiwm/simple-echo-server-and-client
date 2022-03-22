import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("Started Echo Client");
        String ip = System.getProperty("ip");
        int port = Integer.parseInt(System.getProperty("port"));
        try {
            System.out.println("Waiting for connection");
            InetAddress inetAddress = InetAddress.getByName(ip);

            try (Socket clientSocket = new Socket(inetAddress, port);
                 PrintWriter out = new PrintWriter(
                         clientSocket.getOutputStream(), true);
                 BufferedReader bufferedReader = new BufferedReader(
                         new InputStreamReader(
                                 clientSocket.getInputStream()))) {

                System.out.println("Connected to server at " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.println("Enter text: ");
                    String inputLine = scanner.nextLine();
                    if (inputLine.equalsIgnoreCase("quit")) {
                        break;
                    }
                    out.println(inputLine);
                    String response = bufferedReader.readLine();
                    System.out.println("Server response: " + response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            System.out.println("Unknown ip address");
        }
    }
}
