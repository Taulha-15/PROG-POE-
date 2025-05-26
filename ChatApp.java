import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class ChatApp {

    private boolean isLoggedIn = false;
    private ArrayList<Message> sentMessages = new ArrayList<>();
    private int totalMessagesSent = 0;

    public static void main(String[] args)
    {
        ChatApp app = new ChatApp();
        app.run();
    }
    public void run()
    {
        if (loginUser ())
        {
            isLoggedIn = true;
            JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
            showMenu();
        }
    }

    private boolean loginUser ()
    {
        //should activate during login
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");

        //if login is sucessful
        return true;
    }

    private void showMenu()
    {
        while (true)
        {
            String menu = "1) Send Messages\n2) Show recently sent messages\n3) Quit";
            String choice = JOptionPane.showInputDialog(menu);

            switch (choice)
            {
                case "1":
                    sendMessage();
                    break;
                case "2":
                    showSentMessages();
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Exiting the application.");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }

    private void sendMessage()
    {
        int numMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages do you want to send?"));

        for (int i = 0; i < numMessages; i++)
        {
            String recipient = JOptionPane.showInputDialog("Enter recipient's cell number:");
            String messageText = JOptionPane.showInputDialog("Enter your message (max 250 characters):");

            if (messageText.length() > 250)
            {
                JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
                continue;
            }
            Message message = new Message(recipient, messageText);
            sentMessages.add(message);
            totalMessagesSent++;
            JOptionPane.showMessageDialog(null, "Message sent: " + message);
        }
    }

    private void showSentMessages()
    {
        StringBuilder messageList = new StringBuilder("Sent Message:\n");

        for (Message message : sentMessages)
    {
        messageList.append(message.toString()).append("\n");
    }
        JOptionPane.showMessageDialog(null, messageList.toString());
    }

    class Message
    {
        private String recipient;
        private String messageText;
        private String messageID;
        private String messageHash;
        public Message(String recipient, String messageText)
        {
            this.recipient = recipient;
            this.messageText = messageText;
            this.messageID = generateMessageID();
            this.messageHash = createMessageHash();
        }
        private String generateMessageID()
        {

            // to generate a random 10-digit number
            Random rand = new Random();
            return String.format("%010d", rand.nextInt(1000000000));
        }
        private String createMessageHash()
        {
            String [] words = messageText.split("");
            return messageID.substring(0, 2) + ":0:" + words[0].toUpperCase() + words[words.length - 1].toUpperCase();
        }
        @Override
        public String toString()
        {
            return "MessageID: "+messageID+", Recipient: "+ recipient +", Message: "+ messageText +", Hash: "+ messageHash;
        }
    }
}
//program does not have a exit feature once message has been sent yet, must still work on that feature