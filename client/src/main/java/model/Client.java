package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private boolean stillRunning;
    private Boolean workingSocket = true;
    private int clientNr = 0;

    Client(String address, int portNumber) {

        try {
            socket = new Socket(address, portNumber);
        } catch (IOException e) {
            System.out.println("This socket does not work.");
            workingSocket = false;
        }

        if(workingSocket) {
            System.out.println("This client is now connected.");
            clientNr++;

            try {
                outputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            setStillRunning(true);
            CommandHandler commandHandler = new CommandHandler();

            try {
                inputStream = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            while(stillRunning) {
                try {
                    commandHandler.run(this, outputStream, inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void stop() throws IOException {
        setStillRunning(false);
        outputStream.close();
        socket.close();
        System.out.println("This client stopped.");
    }

    public boolean getStillRunning() {
        return stillRunning;
    }

    public void setStillRunning(boolean value) {
        stillRunning = value;
    }

    public int getClientNr(){
        return clientNr;
    }
}
