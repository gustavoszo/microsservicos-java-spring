package br.com.mensageria_kafka.pix_consumer.services;

import br.com.mensageria_kafka.pix_consumer.entities.Key;
import br.com.mensageria_kafka.pix_consumer.repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyService {

    @Autowired
    private KeyRepository keyRepository;

    public Key findByKey(String key) {
        return keyRepository.findByKey(key);
    }

}
