package br.com.mensageria_kafka.pix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@EnableKafkaStreams // ativa o processamento de streams Kafka
@SpringBootApplication
public class PixStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(PixStreamApplication.class, args);
	}

}
