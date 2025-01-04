package br.com.mensageria_kafka.pix_producer.controllers;

import br.com.mensageria_kafka.pix_producer.dto.PixCreateDto;
import br.com.mensageria_kafka.pix_producer.dto.PixResponseDto;
import br.com.mensageria_kafka.pix_producer.dto.mapper.PixMapper;
import br.com.mensageria_kafka.pix_producer.services.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private PixService pixService;

    @PostMapping
    public ResponseEntity<PixResponseDto> salvarPix(@RequestBody PixCreateDto pixDto) {
        var pix = pixService.save(PixMapper.toPix(pixDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(PixMapper.toPixResponseDto(pix));
    }
}
