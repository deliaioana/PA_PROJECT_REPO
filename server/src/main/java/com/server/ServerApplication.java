package com.server;


import com.server.graph.BipartiteGraph;
import com.server.graph.Cupid;
import com.server.model.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
    //SpringApplication.run(ServerApplication.class, args);

    //new Server(6666);

        BipartiteGraph graph = new BipartiteGraph();
        graph.generateRandom(20);
        new Cupid(graph);
        System.out.println(graph.getInfoAsJson());
    }
}
