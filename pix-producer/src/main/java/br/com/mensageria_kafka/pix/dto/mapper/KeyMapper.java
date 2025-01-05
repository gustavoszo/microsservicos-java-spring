package br.com.mensageria_kafka.pix.dto.mapper;

import br.com.mensageria_kafka.pix.dto.KeyDto;
import br.com.mensageria_kafka.pix.entities.Key;

public class KeyMapper {

    public static Key toKey(KeyDto keyDto) {
        return new Key(keyDto.getKey());
    }

}
