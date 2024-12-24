import java.io.*;
import java.util.ArrayList;

public class CarnetAdresses {
    private ArrayList<String> listeIP;

    public CarnetAdresses() {
        listeIP = new ArrayList<>();
        getIPListFromFile("IP.txt");
    }

    public CarnetAdresses(int nb) {
        listeIP = new ArrayList<>(nb);
    }

    // Constructeur avec une liste existante
    public CarnetAdresses(ArrayList<String> liste) {
        listeIP = new ArrayList<>(liste);
    }

    // Méthode pour charger les adresses IP depuis un fichier
    private void getIPListFromFile(String fichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listeIP.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fichier " + fichier + " introuvable !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier " + fichier + ": " + e.getMessage());
        }
    }

    // Getter pour la liste des IP
    public ArrayList<String> getListeIP() {
        return listeIP;
    }

    // Setter pour remplacer la liste des IP
    public void setListeIP(ArrayList<String> listeIP) {
        this.listeIP = listeIP;
    }

    // Ajouter une IP à la liste
    public void ajouterIP(String ip) {
        listeIP.add(ip);
    }

    // Supprimer une IP de la liste
    public boolean supprimerIP(String ip) {
        return listeIP.removeIf(a -> a.equalsIgnoreCase(ip));
    }

    // afficher toutes les adresses IP
    public void afficherIP() {
        System.out.println("Adresses IP : " + listeIP);
    }
}
