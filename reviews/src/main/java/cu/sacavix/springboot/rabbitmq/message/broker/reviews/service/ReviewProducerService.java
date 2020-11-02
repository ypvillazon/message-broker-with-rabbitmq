package cu.sacavix.springboot.rabbitmq.message.broker.reviews.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.sacavix.springboot.rabbitmq.message.broker.product.ReviewProductMessage;
import cu.sacavix.springboot.rabbitmq.message.broker.reviews.configuration.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewProducerService {

    private final ObjectMapper mapper = new ObjectMapper() ;
    
    public static final Logger logger = LoggerFactory.getLogger(ReviewProducerService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void message(ReviewProductMessage message) {
        try {
            logger.info("Enviando un nuevo review creado para: [{}] ", message.getId());
            rabbitTemplate.convertAndSend(Constants.REVIEW_CREATED_QUEUE, mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }
}
