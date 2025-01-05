package br.com.mensageria_kafka.pix.repositories;

import br.com.mensageria_kafka.pix.entities.Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Key, Long> {
}
