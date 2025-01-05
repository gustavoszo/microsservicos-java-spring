package br.com.mensageria_kafka.pix.services;

import br.com.mensageria_kafka.pix.entities.Key;
import br.com.mensageria_kafka.pix.repositories.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyService {

    @Autowired
    private KeyRepository keyRepository;

    public Key save(Key key) {
        return keyRepository.save(key);
    }

}
