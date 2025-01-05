package br.com.mensageria_kafka.pix.streams;

import br.com.mensageria_kafka.pix.dto.PixResponseDto;
import br.com.mensageria_kafka.pix.serdes.PixSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PixAggregator {

    @Autowired
    public void aggregator(StreamsBuilder streamsBuilder) {

        KStream<String, PixResponseDto> messageStream = streamsBuilder
                .stream("SENT_PIX", Consumed.with(Serdes.String(), PixSerdes.serdes()))
                .peek((key, value) -> System.out.println("Pix recebido de: " + value.getOriginKey()))
                .filter((key, value) -> value.getValue().compareTo(new BigDecimal(1000)) >= 1);

        messageStream.print(Printed.toSysOut());
        messageStream.to( "PIX_TOPIC_FRAUD_DETECTOR",  Produced.with(Serdes.String(), PixSerdes.serdes()));

    }

}
