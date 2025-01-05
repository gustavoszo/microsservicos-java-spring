package br.com.mensageria_kafka.pix.services;

import br.com.mensageria_kafka.pix.entities.Pix;
import br.com.mensageria_kafka.pix.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PixService {

    @Autowired
    private PixRepository pixRepository;

    @Transactional(readOnly = true)
    public Pix findByIdentifier(String identifier) {
        return pixRepository.findByIdentifier(identifier);
    }

    @Transactional
    public Pix update(Pix pix) {
        return pixRepository.save(pix);
    }

}
