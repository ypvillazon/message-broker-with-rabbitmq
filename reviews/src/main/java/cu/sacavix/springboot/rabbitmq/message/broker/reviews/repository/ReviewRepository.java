package cu.sacavix.springboot.rabbitmq.message.broker.reviews.repository;

import cu.sacavix.springboot.rabbitmq.message.broker.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {

    public List<Review> findByResourceId(String resourceId) ;

}
