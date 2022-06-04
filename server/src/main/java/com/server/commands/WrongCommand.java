package com.server.commands;


import com.server.model.Pigeon;

import java.net.Socket;

public class WrongCommand {
    public synchronized void execute(Socket socket){
        Pigeon.send("Incorrect command. Try typing:\n - start" +
                "\n - stop" +
                "\n - exit", socket);
    }
}
