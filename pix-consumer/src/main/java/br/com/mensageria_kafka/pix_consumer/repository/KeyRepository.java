package br.com.mensageria_kafka.pix_consumer.repository;

import br.com.mensageria_kafka.pix_consumer.entities.Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Key, Long> {

    Key findByKey(String key);

}
