package spring.netflix.tour.eureka.service.subscription;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
//import com.netflix.discovery.DiscoveryClient; //It is native netflix oss implementation (we are using the wrapper)
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@RestController
//@RequestMapping(value="/test/subscription/boot/eureka")
@SpringBootApplication
@EnableEurekaClient
public class SubscriptionServiceBootEurekaController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	Set<String> lstNetworkServices = new HashSet<String>();
	
	/*@RequestMapping(method=RequestMethod.GET, value="networkservices")
	@ResponseBody
	Set<String> getLstNetworkServicesAvailable(){
        discoveryClient.getInstances("tour-network-services").forEach((ServiceInstance s) -> {
            System.out.println(ToStringBuilder.reflectionToString(s));
            lstNetworkServices.add(ToStringBuilder.reflectionToString(s));
        });		
		
		return lstNetworkServices;
	}*/
	
	
	public static void main(String[] args) {
		//This initialization should be used when we need a continuous process that we need to register with Eureka
		//SpringApplication.run(SubscriptionServiceBootEurekaController.class, args);
		
		//This is more usefull in our case because we only need to attach to the Eureka and ask about the service registered
		new SpringApplicationBuilder(SubscriptionServiceBootEurekaController.class).web(false).run(args);
	}
}
