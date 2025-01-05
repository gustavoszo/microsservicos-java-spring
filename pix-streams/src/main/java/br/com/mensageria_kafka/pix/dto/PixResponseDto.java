package br.com.mensageria_kafka.pix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PixResponseDto {

    private String identifier;
    private String originKey;
    private String destinationKey;
    private BigDecimal value;
    private LocalDateTime transferDate;

}
