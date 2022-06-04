package com.server.commands;

import com.server.model.Pigeon;

import java.net.Socket;

public class StartCommand {
    public synchronized void execute(Socket socket){
        //start new frame
        //daca totul e ok -> trimite mesaj ok
        //eroare -> trimite mesaj not ok cred
        Pigeon.send("The application will start now.", socket);
    }
}
