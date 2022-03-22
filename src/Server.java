import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        int port = Integer.parseInt(System.getProperty("port"));
        try (ServerSocket serverSocket = new ServerSocket(port)){

            System.out.println("Listening on " + serverSocket.getInetAddress().getHostAddress() + ":" + serverSocket.getLocalPort() + ".....");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected to client");

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    System.out.println("Server: " + inputLine);
                    out.println(inputLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
