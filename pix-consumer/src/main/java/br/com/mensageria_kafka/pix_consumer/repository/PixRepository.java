package br.com.mensageria_kafka.pix_consumer.repository;

import br.com.mensageria_kafka.pix_consumer.entities.Pix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixRepository extends JpaRepository<Pix, Long> {

    Pix findByIdentifier(String identifier);

}
