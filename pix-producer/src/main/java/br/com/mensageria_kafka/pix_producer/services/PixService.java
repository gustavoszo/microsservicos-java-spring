package br.com.mensageria_kafka.pix_producer.services;

import br.com.mensageria_kafka.pix_producer.dto.PixDto;
import br.com.mensageria_kafka.pix_producer.entities.Pix;
import br.com.mensageria_kafka.pix_producer.repositories.PixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PixService {

    @Value(value = "{spring.kafka.topic.sent.pix}")
    private final String topic;

    private final PixRepository pixRepository;
    private final KafkaTemplate<String, PixDto> kafkaTemplate;

    public Pix save(PixDto pixDto) {
        var pix = pixRepository.save(Pix.toEntity(pixDto));
        kafkaTemplate.send(topic, pixDto.getIdentifier(), pixDto);
        return pix;
    }

}
