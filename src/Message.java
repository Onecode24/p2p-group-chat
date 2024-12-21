import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    String name;
    String message;
    int messageSize;
    LocalDateTime dateTime;

    public Message(String nom, String message, String messageSize, String dateTime) {

        this.name = nom;
        this.message = message;
        this.messageSize = Integer.parseInt(messageSize);
        this.dateTime = LocalDateTime.parse(dateTime);
    }

    public String showMessage() {
        return name + " dit \"" + message + "\" le " + dateToString(dateTime) + " à " + timeToString(dateTime);
    }

    // Getters
    public String getMessage() {
        return message;
    }
    public int getMessageSize() {
        return messageSize;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getName() {
        return name;
    }
    // Setters
    public void setMessage(String message) {
        this.message = message;
    }
    public void setMessageSize(int messageSize) {
        this.messageSize = messageSize;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void setName(String name) {
        this.name = name;
    }


    private String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(formatter);
    }

    private String timeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(formatter);
    }


}
