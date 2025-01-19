package br.com.mensageria_kafka.pix_consumer.consumers;

import br.com.mensageria_kafka.pix_consumer.avro.PixRecord;
import br.com.mensageria_kafka.pix_consumer.dto.PixResponseDto;
import br.com.mensageria_kafka.pix_consumer.entities.enums.StatusPix;
import br.com.mensageria_kafka.pix_consumer.exception.KeyNotFoundException;
import br.com.mensageria_kafka.pix_consumer.services.KeyService;
import br.com.mensageria_kafka.pix_consumer.services.PixService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PixConsumer {

    private final PixService pixService;
    private final KeyService keyService;

    @KafkaListener(topics = "${spring.kafka.topic.sent.pix}", groupId = "pix_validator")
    // Para cada retentativa, o Kafka cria um tópico diferente. O tópico inicial é "SENT_PIX". Então, para a primeira retentativa, é criado o tópico "SENT_PIX-1", para a segunda retentativa, "SENT_PIX-2" e assim por diante.
    @RetryableTopic(
            backoff = @Backoff(value = 3000L),
            attempts = "5",
            autoCreateTopics = "true",
            include = KeyNotFoundException.class
    )
    public void pixListener(PixRecord pixRecord) {
        var pix = pixService.findByIdentifier(pixRecord.getIdentifier().toString());

        var originKey = keyService.findByKey(pixRecord.getOriginKey().toString());
        var destinationKey = keyService.findByKey(pixRecord.getDestinationKey().toString());

        if (originKey != null && destinationKey != null) {
            pix.setStatus(StatusPix.PROCESSED);
            pixService.update(pix);
            return;
        }
        pix.setStatus(StatusPix.ERROR);
        pixService.update(pix);
        throw new KeyNotFoundException("Key not found");
    }
    
}
