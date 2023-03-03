package kz.dar.tech.eventstoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EventStoreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventStoreApiApplication.class, args);
    }

}
