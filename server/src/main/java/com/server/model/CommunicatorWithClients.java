package com.server.model;

import com.server.commands.StartCommand;
import com.server.commands.WrongCommand;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static com.server.model.Server.stopServer;

public class CommunicatorWithClients extends Thread{
    public Socket socket = null ;
    public Boolean exceptionWasThrown = false;
    public String command = "";
    public Boolean clientExited = false;
    private WrongCommand wrongCommand = new WrongCommand();
    private StartCommand startCommand = new StartCommand();

    public CommunicatorWithClients (Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream;

            while(!command.equals("stop")) {
                if(clientExited) {
                    break;
                }
                try {
                    command = inputStream.readUTF();
                }
                catch (IOException e) {
                    System.out.println("This socket is not active.");
                    socket.close();
                    exceptionWasThrown = true;
                }

                if(!clientExited)
                    System.out.println("The command received was: " + command);

                if(!exceptionWasThrown) {
                    try {
                        outputStream = new DataOutputStream(socket.getOutputStream());
                        executeCommand(command);
                        if(command.equals("exit")) {
                            clientExited = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeCommand(String command) {
        String firstWord = command.split(" ")[0];

        switch(firstWord) {
            case "exit":
                try {
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF("This client will now stop.");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "start":
                executeStart();
                break;
            case "stop":
                stopServer();
                break;
            default:
                executeWrongCommand();
                break;
        }
    }

    private void executeWrongCommand() {
        wrongCommand.execute(socket);
    }

    private void executeStart() {
        startCommand.execute(socket);
    }
}
