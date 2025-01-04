package br.com.mensageria_kafka.pix_producer.repositories;

import br.com.mensageria_kafka.pix_producer.entities.Pix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixRepository extends JpaRepository<Pix, Long> {

}
