package spring.netflix.tour.eureka.service.network;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/test/network/boot")
@SpringBootApplication
public class NetworkServiceBootController {
	
	static Map<String, Map<String, String>> lstIPPerRegion = new HashMap<String, Map<String, String>>();
	
	static{
		
		Map<String, String> lstIpPeru = new HashMap<String, String>();
		lstIpPeru.put("David", "192.168.14.1");
		lstIpPeru.put("Erik", "192.168.14.2");
		lstIpPeru.put("Raul", "192.168.14.3");
		lstIPPerRegion.put("peru", lstIpPeru);
		
		Map<String, String> lstIpUSA = new HashMap<String, String>();
		lstIpUSA.put("Yim", "172.168.14.1");
		lstIpUSA.put("Tom", "172.168.14.2");
		lstIpUSA.put("Peter", "172.168.14.3");
		lstIPPerRegion.put("usa", lstIpUSA);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="iplist")
	@ResponseBody 
	Map<String, Map<String, String>> getIPList(){
		return lstIPPerRegion;
	}
    
	@RequestMapping(method=RequestMethod.GET, value="test")
	String getHi(){
		return "Hi from boot!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(NetworkServiceBootController.class, args);
	}
}
