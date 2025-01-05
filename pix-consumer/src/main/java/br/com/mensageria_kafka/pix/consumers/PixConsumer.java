package br.com.mensageria_kafka.pix.consumers;

import br.com.mensageria_kafka.pix.dto.PixResponseDto;
import br.com.mensageria_kafka.pix.entities.enums.StatusPix;
import br.com.mensageria_kafka.pix.exception.KeyNotFoundException;
import br.com.mensageria_kafka.pix.services.KeyService;
import br.com.mensageria_kafka.pix.services.PixService;
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
    public void pixListener(PixResponseDto pixDto) {
        System.out.println(pixDto.getIdentifier());
        var pix = pixService.findByIdentifier(pixDto.getIdentifier());
        System.out.println(pix.getStatus().toString());

        var originKey = keyService.findByKey(pixDto.getOriginKey());
        var destinationKey = keyService.findByKey(pixDto.getDestinationKey());

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
