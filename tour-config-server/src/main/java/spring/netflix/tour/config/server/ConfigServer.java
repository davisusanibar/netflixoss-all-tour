package spring.netflix.tour.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author daviddali
 * To configure and manage our application properties
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ConfigServer.class, args);
    }
}