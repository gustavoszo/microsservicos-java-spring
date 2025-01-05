package br.com.mensageria_kafka.pix.controllers;

import br.com.mensageria_kafka.pix.dto.KeyDto;
import br.com.mensageria_kafka.pix.dto.mapper.KeyMapper;
import br.com.mensageria_kafka.pix.entities.Key;
import br.com.mensageria_kafka.pix.services.KeyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/key")
public class KeyController {

    @Autowired
    private KeyService keyService;

    @PostMapping ResponseEntity<Key> create(@Valid @RequestBody KeyDto keyDto) {
        var key = keyService.save(KeyMapper.toKey(keyDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(key);
    }

}
