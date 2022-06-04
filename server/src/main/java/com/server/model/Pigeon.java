package com.server.model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Pigeon {
    static public void send(String message, Socket socket) {
        try {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(message);
        } catch (IOException e) {
            System.out.println("Message could not be sent.");
        }
    }
}

