package br.com.mensageria_kafka.pix_producer.services;

import br.com.mensageria_kafka.pix_producer.dto.PixCreateDto;
import br.com.mensageria_kafka.pix_producer.dto.PixResponseDto;
import br.com.mensageria_kafka.pix_producer.dto.mapper.PixMapper;
import br.com.mensageria_kafka.pix_producer.entities.Pix;
import br.com.mensageria_kafka.pix_producer.entities.enums.StatusPix;
import br.com.mensageria_kafka.pix_producer.repositories.PixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PixService {

    @Value(value = "{spring.kafka.topic.sent.pix}")
    private final String topic;

    private final PixRepository pixRepository;
    private final KafkaTemplate<String, PixResponseDto> kafkaTemplate;

    @Transactional
    public Pix save(Pix pix) {
        pix.setStatus(StatusPix.PROCESSING);
        pix.setTransferDate(LocalDateTime.now());
        pixRepository.save(pix);

        kafkaTemplate.send(topic, pix.getIdentifier(), PixMapper.toPixResponseDto(pix));
        return pix;
    }

}
