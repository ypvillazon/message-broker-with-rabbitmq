package cu.sacavix.springboot.rabbitmq.message.broker.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cu.sacavix.springboot.rabbitmq.message.broker.product.ReviewProductMessage;
import cu.sacavix.springboot.rabbitmq.message.broker.product.configuration.Constants;
import cu.sacavix.springboot.rabbitmq.message.broker.product.entity.Product;
import cu.sacavix.springboot.rabbitmq.message.broker.product.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class ReviewProductConsumerService {

    private final ObjectMapper mapper = new ObjectMapper() ;

    @Autowired
    private ProductRepository productRepository ;

    /**
     * Receiving notifications to create the person
     */
    @RabbitListener(queues = Constants.REVIEW_CREATED_QUEUE)
    public void rabbitListener(String in) {
        try {
            ReviewProductMessage reviewProductMessage = mapper.readValue(in, ReviewProductMessage.class);
            Optional<Product> o = productRepository.findById(reviewProductMessage.getId()) ;
            if(o.isPresent()) {
                Product product = o.get() ;
                product.setNumberReviews(reviewProductMessage.getAmount());
                product.setReviewAverage(reviewProductMessage.getAvg());
                productRepository.saveAndFlush(product) ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
