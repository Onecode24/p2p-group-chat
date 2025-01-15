
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;

public class Sender {
    ArrayList<String> IPList ;
    private final static int RECEIVER_PORT = 2024;

    public Sender(String[] args)  {

        System.out.println("PROGRAMME CLIENT");
        // Vérification des arguments
        if (args.length < 1) {
            System.out.println("Usage: java Client <username>");
            return;
        }

        String username = args[0];
        System.out.println("Username: " + username);

        // get the Address_Book
        CarnetAdresses carnetAdresses = new CarnetAdresses();

        // create reader for user message input
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader userInput = new BufferedReader(inputStreamReader);

        IPList  = carnetAdresses.getListeIP();
        System.out.println("Adresses IP chargées : " + IPList);

        do {
            try {
                // Demander le message à l'utilisateur
                System.out.print("Entrez votre message (ou tapez 'exit' pour quitter) : ");
                String userMessage = userInput.readLine();

                if (userMessage.equalsIgnoreCase("exit")) break;

                // Créer un objet Message
                // Message msg = new Message(username, inputMessage, Integer.toString(inputMessage.length()), LocalDateTime.now().toString());

                // Envoyer le message à toutes les adresses IP
                for (String ip : IPList) {
                    Socket socket = new Socket(ip, RECEIVER_PORT);
                    BufferedWriter socketContent = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    // Écriture des données
                    socketContent.write(username);
                    socketContent.newLine();
                    socketContent.write(LocalDateTime.now().toString().replace('T',' '));
                    socketContent.newLine();
                    socketContent.write(Integer.toString(userMessage.length()));
                    socketContent.newLine();
                    socketContent.write(userMessage);
                    socketContent.newLine();
                    socketContent.flush();

                    System.out.println("Message envoyé à " + ip);
                    System.out.println("date format: " + LocalDateTime.now().toString().replace('T',' '));
                    socket.close();
            }

            }catch (Exception e){
                System.err.println("Erreur lors de l'envoi du message " + e.getMessage());
            }

        }while (true);
        // close inputStreamReader and the program
        try {
            inputStreamReader.close();
            System.out.println("Fermeture du programme client.");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public static void main(String[] args) {
        Sender sender = new Sender(args);
    }

}
