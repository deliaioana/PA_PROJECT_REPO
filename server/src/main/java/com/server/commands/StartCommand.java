package com.server.commands;

import com.server.model.Pigeon;
import com.server.graphic.GraphicApplication;

import java.net.Socket;

public class StartCommand {
    public synchronized void execute(Socket socket, int counter){
        new GraphicApplication(counter).setVisible(true);
        Pigeon.send("The application will start now.", socket);
    }
}
