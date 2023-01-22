import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        // Prompt the user for their name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // Connect to the server
        Socket socket = new Socket("localhost", 8000);

        // Create input and output streams
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        // Send the user's name to the server
        output.println(name);

        // Create a new thread to read messages from the server
        new Thread(new ServerMessageHandler(input)).start();

        // Continuously read messages from the user
        while (true) {
            String message = scanner.nextLine();
            output.println(message);
        }
    }
}

class ServerMessageHandler implements Runnable {
    private BufferedReader input;

    public ServerMessageHandler(BufferedReader input) {
        this.input = input;
    }

    public void run() {
        try {
            // Continuously read messages from the server
            while (true) {
                String message = input.readLine();
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
