package cu.sacavix.springboot.rabbitmq.message.broker.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class Run {
    
    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }
}
