package br.com.stoom.store.connections;


import br.com.stoom.store.constantes.RabbitMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQConnection.class);

    private static final String NAME_EXCHANGE = "amq.direct";

    private final AmqpAdmin amqpAdmin;

    @ Autowired
    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String nameQueue) {
        return new Queue(nameQueue, true, false, false);
    }

    private DirectExchange directexchange() {
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding relationship(Queue queue, DirectExchange direct) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, direct.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add() {
        log.info("Declaring queues, exchange, and bindings for RabbitMQ.");

        Queue queueProduct = this.queue(RabbitMQConstants.QUEUE_PRODUCT);
        Queue queueBrand = this.queue(RabbitMQConstants.QUEUE_BRAND);
        Queue queueCategory = this.queue(RabbitMQConstants.QUEUE_CATEGORY);

        DirectExchange direct = this.directexchange();

        Binding callProduct = this.relationship(queueProduct, direct);
        Binding callBrand = this.relationship(queueBrand, direct);
        Binding callCategory = this.relationship(queueCategory, direct);

        this.amqpAdmin.declareQueue(queueProduct);
        this.amqpAdmin.declareQueue(queueBrand);
        this.amqpAdmin.declareQueue(queueCategory);

        this.amqpAdmin.declareExchange(direct);

        this.amqpAdmin.declareBinding(callProduct);
        this.amqpAdmin.declareBinding(callBrand);
        this.amqpAdmin.declareBinding(callCategory);

        log.info("RabbitMQ setup completed.");
    }
}