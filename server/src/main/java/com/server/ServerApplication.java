package com.server;


import graphic.GraphicApplication;

import com.server.graph.BipartiteGraph;
import com.server.model.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
    //SpringApplication.run(ServerApplication.class, args);

    new Server(6666);

    }
}
