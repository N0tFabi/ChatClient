import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        // Create a list to store connected clients
        List<ClientHandler> clients = new ArrayList<>();

        // Create a ServerSocket object and start listening for connections
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Chat server started on port 8000");

        while (true) {
            // Accept a new client connection
            Socket socket = serverSocket.accept();

            // Create a new ClientHandler object for the client
            ClientHandler client = new ClientHandler(socket, clients);

            // Add the new client to the list of connected clients
            clients.add(client);

            // Create a new thread to handle the client
            new Thread(client).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private String name;
    private List<ClientHandler> clients;
    private BufferedReader input;
    private PrintWriter output;

    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;

        try {
            // Create input and output streams for the socket
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            // Read the client's name from the input stream
            name = input.readLine();
            System.out.println(name + " connected");

            // Send a message to all connected clients to inform them of the new client
            for (ClientHandler client : clients) {
                client.output.println(name + " joined the chat.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            // Continuously read messages from the client
            while (true) {
                String message = input.readLine();

                // Send the message to all connected clients
                for (ClientHandler client : clients) {
                    client.output.println(name + ": " + message);
                }
            }
        } catch (IOException e) {
            System.out.println(name + " disconnected");
        } finally {
            // Remove the client from the list of connected clients
            clients.remove(this);

            // Send a message to all connected clients to inform them of the client disconnection
            for (ClientHandler client : clients) {
                client.output.println(name + " left the chat.");
            }
        }
    }
}