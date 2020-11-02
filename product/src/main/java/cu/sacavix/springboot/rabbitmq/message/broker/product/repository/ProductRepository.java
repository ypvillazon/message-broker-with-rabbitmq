package cu.sacavix.springboot.rabbitmq.message.broker.product.repository;

import cu.sacavix.springboot.rabbitmq.message.broker.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
