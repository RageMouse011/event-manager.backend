package kz.dar.tech.eventdiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EventDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventDiscoveryServiceApplication.class, args);
	}

}
