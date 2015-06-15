package spring.netflix.tour.eureka.service.subscription;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
//import com.netflix.discovery.DiscoveryClient; //It is native netflix oss implementation (we are using the wrapper)
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SubscriptionServiceRunEurekaFeignController implements CommandLineRunner {
	
	@Autowired
	TestNetworkServices  testNetworkServicesWithFeign;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	Set<String> lstNetworkServices = new HashSet<String>();
	
	
	public static void main(String[] args) {
		//This initialization should be used when we need a continuous process that we need to register with Eureka
		//SpringApplication.run(SubscriptionServiceBootEurekaController.class, args);
		
		//This is more usefull in our case because we only need to attach to the Eureka and ask about the service registered
		new SpringApplicationBuilder(SubscriptionServiceRunEurekaFeignController.class).web(false).run(args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(testNetworkServicesWithFeign.getIPList());
	}
		
	@FeignClient("tour-network-services-app")
	interface TestNetworkServices {
	    @RequestMapping(method = RequestMethod.GET, value = "/test/network/boot/eureka/iplist")
	    public Map<String, Map<String, String>> getIPList();
	}
}
