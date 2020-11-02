package cu.sacavix.springboot.rabbitmq.message.broker.product;

import java.io.Serializable;

public class ReviewProductMessage implements Serializable {

    private String id ;
    private float avg ;
    private int amount ;

    public ReviewProductMessage() {}

    public ReviewProductMessage(String id, float avg, int amount) {
        this.id = id;
        this.avg = avg;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
