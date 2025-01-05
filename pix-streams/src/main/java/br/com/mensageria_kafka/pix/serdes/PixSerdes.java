package br.com.mensageria_kafka.pix.serdes;

import br.com.mensageria_kafka.pix.dto.PixResponseDto;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

// Serdes.WrapperSerde<PixResponseDto>, é uma classe útil para criar serdes personalizados em Kafka Streams.
public class PixSerdes extends Serdes.WrapperSerde<PixResponseDto> {

    public PixSerdes() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(PixResponseDto.class));
    }

    // O Serde é uma combinação de Serializador e Deserializador que o Kafka Streams usa para trabalhar com os dados.
    public static Serde<PixResponseDto> serdes() {
        JsonSerializer<PixResponseDto> serializer = new JsonSerializer<>();
        JsonDeserializer<PixResponseDto> deserializer = new JsonDeserializer<>(PixResponseDto.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

}
