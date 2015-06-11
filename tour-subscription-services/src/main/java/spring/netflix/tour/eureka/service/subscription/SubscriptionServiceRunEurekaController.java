package spring.netflix.tour.eureka.service.subscription;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
//import com.netflix.discovery.DiscoveryClient; //It is native netflix oss implementation (we are using the wrapper)
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SubscriptionServiceRunEurekaController implements CommandLineRunner {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	Set<String> lstNetworkServices = new HashSet<String>();
	
	
	public static void main(String[] args) {
		//This initialization should be used when we need a continuous process that we need to register with Eureka
		//SpringApplication.run(SubscriptionServiceBootEurekaController.class, args);
		
		//This is more usefull in our case because we only need to attach to the Eureka and ask about the service registered
		new SpringApplicationBuilder(SubscriptionServiceRunEurekaController.class).web(false).run(args);
	}


	@Override
	public void run(String... args) throws Exception {
		List<String> lstServices = discoveryClient.getServices();
		System.out.println("-----------------------Services Available-----------------------");
		for (String service : lstServices) {			
			System.out.println("Service: "+service);
			System.out.println("----------------------------------------------------------------");
			discoveryClient.getInstances(service).forEach((ServiceInstance s) -> {				
				System.out.println("s: "+ToStringBuilder.reflectionToString(s));
	        	System.out.println("s.getUri(): "+s.getUri());
	        	System.out.println("s.getHost(): "+s.getHost());
	        	System.out.println("s.getPort(): "+s.getPort());
	        	System.out.println("s.getServiceId(): "+s.getServiceId());	
	        	System.out.println("**********************************************************");
	        });
			System.out.println("----------------------------------------------------------------");			
		}
		System.out.println("-----------------------Services Available-----------------------");
	}
}
