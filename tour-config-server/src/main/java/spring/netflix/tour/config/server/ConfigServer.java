package spring.netflix.tour.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author daviddali
 * To configure and manage our application properties
 */
@SpringBootApplication
public class ConfigServer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ConfigServer.class, args);
    }
}