package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CommandHandler {
    private Client client;

    void run(Client client, DataOutputStream outputStream,
             DataInputStream inputStream) throws IOException {

        setClient(client);
        Scanner inputScanner = new Scanner(System.in);

        while(client.getStillRunning()) {
            System.out.println("Enter next command:");

            String command = inputScanner.nextLine();

            if(command.equals("exit")) {
                if(sendCommandToServer(command, outputStream)) {
                    getFeedbackFromServer(inputStream);
                }

                inputScanner.close();
                client.stop();
            }

            else {
                if(sendCommandToServer(command, outputStream)) {
                    getFeedbackFromServer(inputStream);
                }
            }
        }
    }

    private boolean sendCommandToServer(String command, DataOutputStream outputStream){
        try {
            outputStream.writeUTF(command);
        } catch (IOException e) {
            System.out.println("The server is not running anymore.");
            try {
                client.stop();
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
            return false;
        }
        System.out.println("Successfully sent.");
        return true;
    }

    private void getFeedbackFromServer(DataInputStream inputStream) {
        String feedback = "";
        Boolean validFeedback = true;
        try {
            feedback = inputStream.readUTF();
        } catch (IOException e) {
            //e.printStackTrace();
            validFeedback = false;
        }
        if(validFeedback){
            System.out.println("Server feedback: " + feedback);
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
