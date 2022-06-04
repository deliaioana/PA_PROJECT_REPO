package com.server;

import graphic.GraphicApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        int counter = 1 ;
        //SpringApplication.run(ServerApplication.class, args);
        new GraphicApplication(counter++).setVisible(true);
        //new GraphicApplication(counter++).setVisible(true);
    }

}
