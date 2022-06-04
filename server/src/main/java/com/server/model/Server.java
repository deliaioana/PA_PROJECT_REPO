package com.server.model;

import com.server.model.CommunicatorWithClients;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataOutputStream outputStream;
    private static boolean serverRunning = true;
    private int counter = 0;

    public static void stopServer(){
        serverRunning = false;
        System.out.println("The server stopped.");
    }

    public Server(int portNumber) {

        try {
            server = new ServerSocket(portNumber);
            System.out.println("Waiting for a client to connect ...");
            while(serverRunning) {
                socket = server.accept();
                System.out.println("A client connected.");
                new CommunicatorWithClients(socket, ++counter).start();
            }
            try {
                outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeUTF("Server stopped");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
