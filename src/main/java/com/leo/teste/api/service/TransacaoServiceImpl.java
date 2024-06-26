package com.leo.teste.api.service;

import com.leo.teste.api.domain.Conta;
import com.leo.teste.api.domain.Transacao;
import com.leo.teste.api.dto.ContaDTO;
import com.leo.teste.api.dto.TransacaoDTO;
import com.leo.teste.api.enums.TipoTransacaoEnum;
import com.leo.teste.api.exception.ContaNotFoundException;
import com.leo.teste.api.exception.TransacaoNaoRealizadaException;
import com.leo.teste.api.repository.ContaRepository;
import com.leo.teste.api.repository.TransacaoRepository;
import com.leo.teste.api.service.validator.ContaValidator;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
@AllArgsConstructor
public class TransacaoServiceImpl implements TransacaoService{

    private ContaRepository contaRepository;
    private ContaValidator contaValidator;
    private TransacaoRepository transacaoRepository;

    @Override
    @Transactional
    public TransacaoDTO criarTransacao(TransacaoDTO transacao) throws TransacaoNaoRealizadaException {
        Conta conta = contaRepository.findByNumeroConta(transacao.getNumeroConta());
        if(conta==null){
            throw new ContaNotFoundException();
        }
        ContaDTO contaDTO = new ContaDTO(conta.getId(),conta.getNumeroConta(), conta.getSaldo());
        contaValidator.permiteOperacaoNoSaldo(transacao.getValor(),contaDTO );

        transacao.setConta(contaDTO);

        Transacao transacaoToPersist = Transacao.builder().
                conta(conta).
                tipoDeTransacao(TipoTransacaoEnum.fromChar(transacao.getTipoTransacaoChar())).
                valor(transacao.getValor()).
                build();

        Transacao transacaoSalva = transacaoRepository.save(transacaoToPersist);

        BigDecimal novoSaldo = conta.getSaldo().subtract(transacao.getValor());
        conta.setSaldo(novoSaldo);
        contaDTO.setSaldo(novoSaldo);
        contaRepository.save(conta);

        transacaoSalva.setConta(conta);

        return new TransacaoDTO(
                transacaoSalva.getId(),
                contaDTO.getNumeroConta(),
                transacao.getTipoTransacaoChar(),
                TipoTransacaoEnum.fromChar(transacao.getTipoTransacaoChar()),
                contaDTO,
        transacao.getValor(),
        null
        );
    }
}

