package application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import application.config.RabbitMQConfiguration;
import application.model.Product;
@SpringBootApplication
public class TransferCenterApplication implements CommandLineRunner
{
	@Autowired
	private RabbitTemplate rabbitTemplate;
	public static void main(String[] args) throws InterruptedException
	{
		SpringApplication.run(TransferCenterApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception
	{
		Product product = new Product();
		product.setProductId(100);
		product.setName("Laptop");
		product.setQuantity(10);
		System.out.println("Sending message...");
		//If you want to send a string pass a string in place of a object product
		rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName,"message_routing_key", product);
		System.out.println("Message sent successfully...");
	}
}
