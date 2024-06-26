package com.leo.teste.api.service;

import com.leo.teste.api.domain.Conta;
import com.leo.teste.api.dto.ContaDTO;
import com.leo.teste.api.exception.ContaNotFoundException;
import com.leo.teste.api.repository.ContaRepository;
import com.leo.teste.api.service.validator.ContaValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@AllArgsConstructor
public class ContaServiceImpl implements ContaService{

    private ContaRepository contaRepository;

    private ContaValidator contaValidator;
    @Override
    @Transactional
    public ContaDTO createConta(ContaDTO contaDTO) {

        contaValidator.validarSeContaJaEstaCadastrada(contaDTO);
        contaValidator.validarSaldoNaCriacaoDaConta(contaDTO);

        Conta contaToSave = Conta.builder()
                .numeroConta(contaDTO.getNumeroConta())
                .saldo(contaDTO.getSaldo())
                .build();

        Conta retorno = contaRepository.save(contaToSave);
        return new ContaDTO(retorno.getId(),retorno.getNumeroConta(),retorno.getSaldo());
    }



    @Override
    public ContaDTO getContaById(Long contaId) {
        Optional<Conta> optionalConta = contaRepository.findById(contaId);
        if(!optionalConta.isPresent()) {
            throw new ContaNotFoundException();
        }

        return new ContaDTO(
                optionalConta.get().getId(),
                optionalConta.get().getNumeroConta(),
                optionalConta.get().getSaldo()
        );
    }

    @Override
    public Page getAllContas(int pageNumber, int qtdElementos) {
        Pageable paginador = PageRequest.of(pageNumber, qtdElementos);
        return contaRepository.findAll(paginador);
    }

}

