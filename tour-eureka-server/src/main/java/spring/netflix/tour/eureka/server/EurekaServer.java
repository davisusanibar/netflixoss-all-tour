package spring.netflix.tour.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author daviddali
 * 1.- This class starting a eureka server (default eureka app name = /eureka)
 * 2.- At the background this class scan for properties configuration at: Could not locate PropertySource: I/O error on GET request for "http://localhost:8888/tour-eureka-server-app/default/master":Connection refused
 * You could override this behavior at spring.cloud.config.uri: ${vcap.services.config-service.credentials.uri:http://localhost:8888}
 * 3.- This class start at the same time for default a client app that subscribe to the eureka server
 * You could override this behavior at eureka.client.registerWithEureka: false (with this configuration you only start the server and not subscribe a client to this eureka server)
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServer.class, args);		
	}
}

