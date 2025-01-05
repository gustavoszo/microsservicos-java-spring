package br.com.mensageria_kafka.pix.dto.mapper;

import br.com.mensageria_kafka.pix.dto.PixResponseDto;
import br.com.mensageria_kafka.pix.entities.Pix;

public class PixMapper {


    public static PixResponseDto toPixResponseDto(Pix pix) {
        return new PixResponseDto(pix.getIdentifier(), pix.getOriginKey(), pix.getDestinationKey(), pix.getValue(), pix.getTransferDate());
    }

}
