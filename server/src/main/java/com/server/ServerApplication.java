package com.server;


import com.server.graph.BipartiteGraph;
import com.server.graph.Cupid;
import com.server.model.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {

    SpringApplicationBuilder builder = new SpringApplicationBuilder(ServerApplication.class);
    builder.headless(false);
    ConfigurableApplicationContext context = builder.run(args);

    new Server(6666);

        /*BipartiteGraph graph = new BipartiteGraph();
        graph.generateRandom(20);
        new Cupid(graph);
        String info = graph.getInfoAsJson();
        //System.out.println(info);

        BipartiteGraph graph2 = new BipartiteGraph();
        graph2.loadGraphFromString(info);
        System.out.println(graph2.getInfoAsJson());*/
    }
}
