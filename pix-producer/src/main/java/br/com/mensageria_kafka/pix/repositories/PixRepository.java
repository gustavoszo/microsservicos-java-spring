package br.com.mensageria_kafka.pix.repositories;

import br.com.mensageria_kafka.pix.entities.Pix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixRepository extends JpaRepository<Pix, Long> {

}
