package br.com.mensageria_kafka.pix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PixCreateDto {

    @NotBlank
    private String originKey;

    @NotBlank
    private String destinationKey;

    @NotNull
    @Positive
    private BigDecimal value;

}
