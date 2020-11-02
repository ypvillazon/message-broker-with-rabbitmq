package cu.sacavix.springboot.rabbitmq.message.broker.reviews.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class Configuration {

    @Bean
    public Queue queue1() {
        return new Queue(Constants.REVIEW_CREATED_QUEUE);
    }

}
