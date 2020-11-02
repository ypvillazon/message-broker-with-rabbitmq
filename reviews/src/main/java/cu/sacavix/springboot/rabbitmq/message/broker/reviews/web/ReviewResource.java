package cu.sacavix.springboot.rabbitmq.message.broker.reviews.web;

import cu.sacavix.springboot.rabbitmq.message.broker.product.ReviewProductMessage;
import cu.sacavix.springboot.rabbitmq.message.broker.reviews.entity.Review;
import cu.sacavix.springboot.rabbitmq.message.broker.reviews.repository.ReviewRepository;
import cu.sacavix.springboot.rabbitmq.message.broker.reviews.service.ReviewProducerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ReviewResource {

    @Autowired
    private ReviewRepository reviewRepository ;

    @Autowired
    private ReviewProducerService reviewProducerService ;


    @CrossOrigin(origins = "*")
    @PostMapping(value = "/review")
    @ApiOperation(value= "Create review", response = Review.class)
    public ResponseEntity<?> create(@RequestBody Review review) {
        if (review.getValue() >= 0 && review.getValue() <=5 ) {
            Review r = reviewRepository.save(review) ;
            createReviewProductMessage(r) ;
            return ResponseEntity.ok(r) ;
        } else {
            return ResponseEntity.badRequest().body("The evaluation should be an integer betwen 0 - 5");
        }
    }

    private void createReviewProductMessage(Review r) {
        List<Review> reviews = this.reviewRepository.findByResourceId(r.getResourceId());
        int total = 0;
        for (Review review : reviews) {
            total += review.getValue();
        }
        float average = total/reviews.size() ;
        average = ((int) (average * 10)) / 10.0f;
        reviewProducerService.message(new ReviewProductMessage(r.getResourceId(), average, reviews.size()));
    }

}
