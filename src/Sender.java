
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;

public class Sender {
    ArrayList<String> IPList ;
    public Sender(String[] args) {
        System.out.println("PROGRAMME CLIENT");

        // Vérification des arguments
        if (args.length < 1) {
            System.out.println("Usage: java Client <username>");
            return;
        }

        String username = args[0];
        CarnetAdresses carnetAdresses = new CarnetAdresses();

        // Charger les adresses IP depuis le fichier
        /*try (BufferedReader reader = new BufferedReader(new FileReader("adresses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                gestion.listeIP.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier adresses.txt: " + e.getMessage());
            return;
        }*/
        IPList  = carnetAdresses.getListeIP();
        System.out.println("Adresses IP chargées : " + IPList);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Demander le message à l'utilisateur
                System.out.print("Entrez votre message (ou tapez 'exit' pour quitter) : ");
                String inputMessage = scanner.nextLine();

                if (inputMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Fermeture du programme client.");
                    break;
                }

                // Créer un objet Message
               // Message msg = new Message(username, inputMessage, Integer.toString(inputMessage.length()), LocalDateTime.now().toString());

                // Envoyer le message à toutes les adresses IP
                for (String ip : IPList) {
                    try (Socket socket = new Socket(ip, 1048);
                            BufferedWriter b_writer = new BufferedWriter(
                                    new OutputStreamWriter(socket.getOutputStream()))) {

                        // Écriture des données
                        b_writer.write(username);
                        b_writer.newLine();
                        b_writer.write(LocalDateTime.now().toString());
                        b_writer.newLine();
                        b_writer.write(Integer.toString(inputMessage.length()));
                        b_writer.newLine();
                        b_writer.write(inputMessage);
                        b_writer.newLine();
                        b_writer.flush();

                        System.out.println("Message envoyé à " + ip);

                    } catch (IOException e) {
                        System.err.println("Erreur lors de l'envoi du message à " + ip + ": " + e.getMessage());
                    }
                }
            }
            System.exit(0);
        }
    }
}
