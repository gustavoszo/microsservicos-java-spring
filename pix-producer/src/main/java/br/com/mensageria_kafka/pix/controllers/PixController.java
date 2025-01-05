package br.com.mensageria_kafka.pix.controllers;

import br.com.mensageria_kafka.pix.dto.PixCreateDto;
import br.com.mensageria_kafka.pix.dto.PixResponseDto;
import br.com.mensageria_kafka.pix.dto.mapper.PixMapper;
import br.com.mensageria_kafka.pix.services.PixService;
import jakarta.validation.Valid;
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
    public ResponseEntity<PixResponseDto> createPix(@Valid  @RequestBody PixCreateDto pixDto) {
        var pix = pixService.save(PixMapper.toPix(pixDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(PixMapper.toPixResponseDto(pix));
    }
}
