package br.com.mensageria_kafka.pix.streams;

import br.com.mensageria_kafka.pix.dto.PixResponseDto;
import br.com.mensageria_kafka.pix.serdes.PixSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PixAggregator {

    @Autowired
    public void aggregator(StreamsBuilder streamsBuilder) {
        //  Representa um fluxo contínuo de dados. Cada evento é processado de forma independente e não mantém estado entre os eventos.
        KStream<String, PixResponseDto> messageStream = streamsBuilder
                .stream("SENT_PIX", Consumed.with(Serdes.String(), PixSerdes.serdes()))
                .peek((key, value) -> System.out.println("Pix recebido de: " + value.getOriginKey()))
                .filter((key, value) -> value.getValue().compareTo(new BigDecimal(1000)) >= 1);

        messageStream.print(Printed.toSysOut());
        messageStream.to( "PIX_TOPIC_FRAUD_DETECTOR",  Produced.with(Serdes.String(), PixSerdes.serdes()));

        // Representa uma tabela de dados em que os valores podem ser agregados ou atualizados. Ele mantém o estado da última agregação ou valor para cada chave.
        KTable<String, Double> aggregation = streamsBuilder
                .stream("SENT_PIX", Consumed.with(Serdes.String(), PixSerdes.serdes()))
                .peek((key, value) -> System.out.println("Pix recebido de: " + value.getOriginKey()))
                .groupBy((key, value) -> value.getOriginKey())
                .aggregate(
                        () -> 0.0,
                        (key, value, aggregate) -> Double.valueOf(value.getValue().add(BigDecimal.valueOf(aggregate)).toString()),
                        Materialized.with(Serdes.String(), Serdes.Double())
                );

        aggregation.toStream().to( "PIX_TOPIC_AGGREGATION",  Produced.with(Serdes.String(), Serdes.Double()));

    }

}
