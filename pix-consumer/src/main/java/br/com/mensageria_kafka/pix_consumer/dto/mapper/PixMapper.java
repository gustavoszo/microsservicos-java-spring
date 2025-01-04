package br.com.mensageria_kafka.pix_consumer.dto.mapper;

import br.com.mensageria_kafka.pix_producer.dto.PixCreateDto;
import br.com.mensageria_kafka.pix_producer.dto.PixResponseDto;
import br.com.mensageria_kafka.pix_producer.entities.Pix;

public class PixMapper {

    public static Pix toPix(PixCreateDto pixDto) {
        return new Pix(pixDto.getIdentifier(), pixDto.getOriginKey(), pixDto.getOriginKey(), pixDto.getValue());
    }

    public static PixResponseDto toPixResponseDto(Pix pix) {
        return new PixResponseDto(pix.getIdentifier(), pix.getOriginKey(), pix.getOriginKey(), pix.getValue(), pix.getTransferDate());
    }

}
