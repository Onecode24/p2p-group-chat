import java.time.*;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        /*
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        */

        //Message message = new Message("Angelo", "Hello World", "11", LocalDateTime.now().toString());

        //System.out.println(message.showMessage());

        CarnetAdresses carnetAdresses = new CarnetAdresses();
        System.out.println("IPs: "+carnetAdresses.getListeIP());

    }
}