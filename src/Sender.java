
import java.io.*;
import java.net.*;
import java.util.*;

public class Sender {
    public static void main(String[] args) {
        System.out.println("PROGRAMME CLIENT");

        // Vérification des arguments
        if (args.length < 1) {
            System.out.println("Usage: java Client <username>");
            return;
        }

        String username = args[0];
        CarnetAdresses gestion = new CarnetAdresses();

        // Charger les adresses IP depuis le fichier
        try (BufferedReader reader = new BufferedReader(new FileReader("adresses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                gestion.listeIP.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier adresses.txt: " + e.getMessage());
            return;
        }

        System.out.println("Adresses IP chargées : " + gestion.listeIP);

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
                Message msg = new Message(inputMessage, username);

                // Envoyer le message à toutes les adresses IP
                for (String ip : gestion.listeIP) {
                    try (Socket socket = new Socket(ip, 1048);
                            BufferedWriter b_writer = new BufferedWriter(
                                    new OutputStreamWriter(socket.getOutputStream()))) {

                        // Écriture des données
                        b_writer.write(msg.getUsername());
                        b_writer.newLine();
                        b_writer.write(msg.getDate());
                        b_writer.newLine();
                        b_writer.write(String.valueOf(msg.getLength()));
                        b_writer.newLine();
                        b_writer.write(msg.getMessage());
                        b_writer.newLine();
                        b_writer.flush();

                        System.out.println("Message envoyé à " + ip);

                    } catch (IOException e) {
                        System.err.println("Erreur lors de l'envoi du message à " + ip + ": " + e.getMessage());
                    }
                }
            }
        }
    }
}
