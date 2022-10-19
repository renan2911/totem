package com.totem.totem.controller;

import com.totem.totem.dto.TotemDTO;
import com.totem.totem.dto.TotemPrioridadeDTO;
import com.totem.totem.entity.TotemEntity;
import com.totem.totem.service.TotemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/totem")
public class TotemController {

    @Autowired
    TotemService totemService;

    @PostMapping("/senha")
    public ResponseEntity<TotemDTO> gerarSenha(@RequestBody @Validated TotemPrioridadeDTO totemDto){
        return new ResponseEntity<TotemDTO>(totemService.gerarSenhaDeAcordoComAPrioridade(totemDto), HttpStatus.CREATED);
    }

    @PostMapping("/iniciar")
    public String iniciar(){
        return totemService.inicia();
    }
}
