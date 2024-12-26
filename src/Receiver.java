import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Receiver {

    private final static int PORT = 2024;
    ArrayList<Message> messages = new ArrayList<>();

    public Receiver(){
        // Create server socket to establish connection

        try {
            while (true) {
                ServerSocket serverSocket = new ServerSocket(PORT);
                Socket socket = serverSocket.accept();

                InputStream inputStream = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String name = reader.readLine();
                String date = reader.readLine();
                String messageSize = reader.readLine();
                String message = reader.readLine();

                serverSocket.close();

                if(Integer.parseInt(messageSize) != message.length()) {
                    System.out.println("Warning: Message size is wrong, it must be "+ message.length());
                    continue;
                }
                // create Message object and add it to the Array bb
                Message newMessage = new Message(name,message,messageSize, date);
                messages.add(newMessage);

                System.out.println(newMessage.showMessage());

                // add message to log
                this.logMessages(newMessage);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // write the message in the log file
    private void logMessages(Message message) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("messages.log");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);

        writer.write(message.showMessage());
        writer.newLine();
        writer.flush();
        writer.close();

    }

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
    }

}
