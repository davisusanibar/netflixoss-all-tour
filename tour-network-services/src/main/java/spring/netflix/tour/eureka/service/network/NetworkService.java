package spring.netflix.tour.eureka.service.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NetworkService {
	public static void main(String[] args) {
		SpringApplication.run(NetworkService.class, args);
	}
}
