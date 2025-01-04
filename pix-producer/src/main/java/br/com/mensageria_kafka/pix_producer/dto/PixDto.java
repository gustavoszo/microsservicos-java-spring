package br.com.mensageria_kafka.pix_producer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PixDto {

    @NotBlank
    private String identifier;

    @NotBlank
    private String originKey;

    @NotBlank
    private String destinationKey;

    @NotNull
    @Positive
    private Double value;

}
