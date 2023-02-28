package Yuval_Almog.virtualPrinterV_1;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class VirtualPrinterV1Application {
	@Autowired
	RabbitTemplate rabbitTemplate;

	public static void main(String[] args)throws IOException, TimeoutException {
		SpringApplication.run(VirtualPrinterV1Application.class, args);
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("testing", false, false, false, null);
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Select an option:");
			System.out.println("1. select printer to print");
			System.out.println("2. random printer print" );
			System.out.println("3. Quit");
			int option = scanner.nextInt();
			if (option == 1) {
				System.out.print("Enter printer id: ");
				String message = scanner.next();
				channel.basicPublish("", "testing", null, message.getBytes());
				System.out.println("Sent message: " + message);
			} else if (option ==2) {
				String message = "random";
				channel.basicPublish("", "testing", null, message.getBytes());
				System.out.println("Sent message: " + message);
			}else if (option == 3) {
				System.exit(0);
				break;
			} else {
				System.out.println("Invalid option!");
			}
		}





	}



}
