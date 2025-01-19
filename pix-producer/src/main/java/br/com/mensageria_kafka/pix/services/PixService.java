package br.com.mensageria_kafka.pix.services;

import br.com.mensageria_kafka.pix.avro.PixRecord;
import br.com.mensageria_kafka.pix.dto.PixResponseDto;
import br.com.mensageria_kafka.pix.dto.mapper.PixMapper;
import br.com.mensageria_kafka.pix.entities.Pix;
import br.com.mensageria_kafka.pix.entities.enums.StatusPix;
import br.com.mensageria_kafka.pix.repositories.PixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PixService {

    @Value("${spring.kafka.topic.sent.pix}")
    private String topic;

    private final PixRepository pixRepository;
    private final KafkaTemplate<String, PixRecord> kafkaTemplate;

    @Transactional
    public Pix save(Pix pix) {
        pix.setIdentifier(UUID.randomUUID().toString());
        pix.setStatus(StatusPix.PROCESSING);
        pix.setTransferDate(LocalDateTime.now());
        pixRepository.save(pix);

        var pixRecord = PixRecord.newBuilder()
                .setIdentifier(pix.getIdentifier())
                .setOriginKey(pix.getOriginKey())
                .setDestinationKey(pix.getDestinationKey())
                .setTransferDate(pix.getTransferDate().toString())
                .setValue(pix.getValue().doubleValue())
                .build();

        kafkaTemplate.send(topic, pix.getIdentifier(), pixRecord);
        return pix;
    }

}
