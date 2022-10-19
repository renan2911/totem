package com.totem.totem.service;

import com.totem.totem.dto.TotemDTO;
import com.totem.totem.dto.TotemPrioridadeDTO;
import com.totem.totem.entity.ControlaQtdSenhaEntity;
import com.totem.totem.entity.TotemEntity;
import com.totem.totem.repository.ControlaQtdSenhaRepository;
import com.totem.totem.repository.TotemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

        while (!cond){
            if(prioridade.equalsIgnoreCase("S")){
                senha = "AP-"+contaProxSenha();;
            }
            if(totemRepository.findBySenha(senha).isEmpty()){
                cond = true;
            }
        }

        return senha;
    }

    public Integer contaProxSenha(){
        ControlaQtdSenhaEntity controlaSenha = new ControlaQtdSenhaEntity();

        controlaSenha.setQtdSenhaGeradaAtual(controlaSenha.getQtdSenhaGeradaAtual()+1);

        qtdSenha.save(controlaSenha);

        return controlaSenha.getQtdSenhaGeradaAtual();
    }

    public TotemDTO retornaSenha(String senha){
        return TotemDTO.builder().senha("Sua senha: "+ senha).build();
    }
}
