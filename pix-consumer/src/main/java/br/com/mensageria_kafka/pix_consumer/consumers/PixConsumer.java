package br.com.mensageria_kafka.pix_consumer.consumers;

import br.com.mensageria_kafka.pix_consumer.dto.PixResponseDto;
import br.com.mensageria_kafka.pix_consumer.entities.enums.StatusPix;
import br.com.mensageria_kafka.pix_consumer.services.KeyService;
import br.com.mensageria_kafka.pix_consumer.services.PixService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PixConsumer {

    private final PixService pixService;
    private final KeyService keyService;

    @KafkaListener(topics = "${spring.kafka.topic.sent-pix}", groupId = "pix_validator")
    public void pixListener(PixResponseDto pixDto) {
        var pix = pixService.findByIdentifier(pixDto.getIdentifier());

        var originKey = keyService.findByKey(pixDto.getOriginKey());
        var destinationKey = keyService.findByKey(pixDto.getDestinationKey());

        if (originKey != null && destinationKey != null) {
            pix.setStatus(StatusPix.PROCESSED);
            return;
        }
        pix.setStatus(StatusPix.ERROR);
    }
    
}
