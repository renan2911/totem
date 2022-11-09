package com.totem.totem.service;

import com.totem.totem.dto.TotemDTO;
import com.totem.totem.dto.TotemPrioridadeDTO;
import com.totem.totem.entity.ControlaQtdSenhaEntity;
import com.totem.totem.entity.TotemEntity;
import com.totem.totem.repository.ControlaQtdSenhaRepository;
import com.totem.totem.repository.TotemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class TotemService {

    @Autowired
    private TotemRepository totemRepository;

    @Autowired
    private ControlaQtdSenhaRepository qtdSenha;

    public TotemDTO gerarSenhaDeAcordoComAPrioridade(TotemPrioridadeDTO prioridade){
        TotemEntity totemEntity = new TotemEntity();
        totemEntity.setData(LocalDateTime.now());
        totemEntity.setPrioridade(prioridade.getPrioridade());
        totemEntity.setSenha(geradorSenha(prioridade.getPrioridade()));

        totemRepository.save(totemEntity);

        return retornaSenha(totemEntity.getSenha());
    }

    public String geradorSenha(String prioridade){
        boolean cond = false;
        String senha = null;
        DateTimeFormatter br = DateTimeFormatter.ofPattern("dd-MM");
        LocalDate data;

        data = LocalDate.parse(LocalDate.now().toString(), br);

        senha = prioridade+"-"+contaProxSenha()+"-"+data;


        return senha;
    }

    public Integer contaProxSenha(){
        Optional<ControlaQtdSenhaEntity> controlsSena = qtdSenha.findById(1L);

        ControlaQtdSenhaEntity control = controlsSena.get();

        control.setQtdSenhaGeradaAtual(controlsSena.get().getQtdSenhaGeradaAtual()+1);

        qtdSenha.save(control);

        return control.getQtdSenhaGeradaAtual();
    }

    public TotemDTO retornaSenha(String senha){
        return TotemDTO.builder().senha("Sua senha: "+ senha).build();
    }

    public String inicia(){
        ControlaQtdSenhaEntity controlsSena = new ControlaQtdSenhaEntity();

        controlsSena.setQtdSenhaGeradaAtual(0);

        qtdSenha.save(controlsSena);

        return "Sistema iniciado com sucesso! :)";
    }
}
